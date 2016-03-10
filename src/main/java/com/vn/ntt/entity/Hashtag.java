package com.vn.ntt.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by bangnl on 3/10/16.
 */
@Document
public class Hashtag extends Model{

    private String hash;

    private String type;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
