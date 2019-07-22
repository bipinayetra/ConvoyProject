package com.bipinayetra.convoy.model;

import androidx.room.Embedded;

public class Origin{
    public String city;
    public String state;
    @Embedded
    public Pickup pickup;

    @Override
    public String toString() {
        return "Origin{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pickup=" + pickup +
                '}';
    }
}
