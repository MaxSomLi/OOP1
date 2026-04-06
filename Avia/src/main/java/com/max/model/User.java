package com.max.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    int id;
    private String name;
    private String password;
}