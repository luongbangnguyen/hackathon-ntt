package org.spring.mongdb.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by bangnl on 3/9/2016.
 */

@Document
public class Buddy extends Model{
    private String token;
    private String name;
    private Double lat;
    private Double lon;
    private Date lastUpTime;

    @DBRef
    private List<Hash> hashList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }

    public List<Hash> getHashList() {
        return hashList;
    }

    public void setHashList(List<Hash> hashList) {
        this.hashList = hashList;
    }
}
