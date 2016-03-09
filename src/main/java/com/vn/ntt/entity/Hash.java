package com.vn.ntt.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by bangnl on 3/9/2016.
 */
@Document
public class Hash extends Model{
    private String tag;
    private String type;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
