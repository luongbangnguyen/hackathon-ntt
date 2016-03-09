package com.vn.ntt.entity;

import com.vn.ntt.enums.Status;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by bangnl on 3/9/2016.
 */

@Document
public class Buddy extends Model {
    private String token;
    private String name;
    private double[] location ;
    private Date lastUpTime;
    private Status status;
    private Double radius;

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

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
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

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
