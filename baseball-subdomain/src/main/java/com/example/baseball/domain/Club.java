package com.example.baseball.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Club {
    @Id
    String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }  
}
