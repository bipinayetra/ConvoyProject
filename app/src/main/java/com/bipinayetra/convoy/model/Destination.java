package com.bipinayetra.convoy.model;

import androidx.room.Embedded;

public class Destination{
    public String city;
    public String state;
    @Embedded
    public Pickup pickup;

    @Override
    public String toString() {
        return "Destination{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pickup=" + pickup +
                '}';
    }
}
