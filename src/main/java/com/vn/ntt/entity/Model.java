package com.vn.ntt.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by bangnl on 2/25/16.
 */
public class Model {

    public Model(){
        setLastUpTime(new Date());
    }

    @Id
    private String id;

    private Date lastUpTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastUpTime() {
        return lastUpTime;
    }

    public void setLastUpTime(Date lastUpTime) {
        this.lastUpTime = lastUpTime;
    }
}
