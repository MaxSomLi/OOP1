package com.max.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flight {
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private String status;
}