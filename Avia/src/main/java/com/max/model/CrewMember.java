package com.max.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CrewMember {

    private int id;
    private String name;
    private String role;

    public CrewMember() {}

    @Override
    public String toString() {
        return "CrewMember{id=" + id + ", name='" + name + "'" + ", role='" + role + "'" + "}";
    }
}