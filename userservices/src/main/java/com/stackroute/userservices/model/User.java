package com.stackroute.userservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;
    @Column(unique=true)
    private String userName;
    private String email;
    private String password;
    private String qualification;

    public void copyWithoutNotNull(User user){
        if(StringUtils.isNotEmpty(user.getUserName())){
        this.userName= user.userName;
    }
    if(StringUtils.isNotEmpty(user.getEmail())){
        this.email= user.email;
    }
    if(StringUtils.isNotEmpty(user.getQualification())){
        this.qualification= user.qualification;
    }
    
    }
}
