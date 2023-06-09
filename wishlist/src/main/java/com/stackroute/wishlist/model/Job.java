package com.stackroute.wishlist.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("role")
    private String role;

    @JsonProperty("company")
    private String company;

    @JsonProperty("landing_page")
    private String landing_page;
}
