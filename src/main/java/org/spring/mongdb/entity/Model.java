package org.spring.mongdb.entity;

import org.springframework.data.annotation.Id;
/**
 * Created by bangnl on 2/25/16.
 */
public class Model {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
