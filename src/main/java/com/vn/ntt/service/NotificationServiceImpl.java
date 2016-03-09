package com.vn.ntt.service;

import com.vn.ntt.constant.SystemConstant;
import com.vn.ntt.entity.Buddy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by bangnl on 3/9/16.
 */

@Service
public class NotificationServiceImpl implements NotificationService{

    @Value("#{"+ SystemConstant.AUTHORIZATION+"}")
    private String authorizationKey;

    @Value("{"+SystemConstant.SERVER_GCM+"}")
    private String serverGCM;

    @Override
    public boolean notificationListBuddy(Buddy sender, List<Buddy> receivers) {

        if(CollectionUtils.isEmpty(receivers)){
            throw new IllegalArgumentException("list receivers can not null");
        }

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = getHeaders();
        receivers.stream().forEach(buddy -> {
           sendDataToserverGCM(sender, headers, template, buddy.getToken());
        });
        return true;
    }

    @Override
    public boolean notificationToOneBuddy(Buddy sender, Buddy receiver) {
        sendDataToserverGCM(sender,receiver.getToken());
        return true;
    }


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","key="+this.authorizationKey);
        return headers;
    }

    private void sendDataToserverGCM(Buddy data, String tokenReceiver){
        HttpHeaders headers =getHeaders();
        RestTemplate template = new RestTemplate();
        this.sendDataToserverGCM(data, headers,template,tokenReceiver);
    }

    private void sendDataToserverGCM(Buddy data, HttpHeaders headers, RestTemplate template, String tokenReceiver){
        Message<Buddy> message = new Message<>(tokenReceiver, data);
        HttpEntity<Message> entity = new HttpEntity<>(message, headers);
        template.exchange(this.serverGCM, HttpMethod.POST,entity, Object.class);
    }

    /**
     * Class DTO for send message
     * @param <T>
     */
    private static class Message<T>{
        private String to;
        private T data;
        Message(String to, T data){
            this.to = to;
            this. data = data;
        }

        public T getData() {
            return data;
        }

        public String getTo() {
            return to;
        }
    }
}
