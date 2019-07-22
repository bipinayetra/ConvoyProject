package com.bipinayetra.convoy.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A JSON array containing offer objects.
 *
 *Response
 * A JSON array containing offer objects.
 *
 * miles - Total miles from origin to destination
 *
 * origin.city - Origin city
 *
 * origin.state - Origin state
 *
 * origin.pickup.start - When the pickup appointment begins
 *
 * origin.pickup.end - When the pickup appointment ends
 *
 * destination.city - Destination city
 *
 * destination.state - Destination state
 *
 * destination.dropoff.start - When the dropoff appointment begins
 *
 * destination.dropoff.end - When the dropoff appointment ends
 *
 * offer - Offer price, in US dollars.
 *
 * Example:
 * {
 *     "miles": "1139",
 *     "origin": {
 *       "city": "West Palm Beach",
 *       "state": "FL",
 *       "pickup": {
 *         "start": "2018-04-30T09:00:00.000Z",
 *         "end": "2018-04-30T11:00:00.000Z"
 *       }
 *     },
 *     "destination": {
 *       "city": "Reading",
 *       "state": "PA",
 *       "dropoff": {
 *         "start": "2018-05-02T09:00:00.000Z",
 *         "end": "2018-05-02T12:00:00.000Z"
 *       }
 *     },
 *     "offer": 2346
 *   }
 */
@Entity(tableName = "offers")
public class Offer {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    public int miles;
    @Embedded(prefix = "origin_")
    public Origin origin;
    @Embedded(prefix = "destination_")
    public Destination destination;
    public String offer;

    @Override
    public String toString() {
        return "Offer{" +
                "uid=" + uid +
                ", miles=" + miles +
                ", origin=" + origin +
                ", destination=" + destination +
                ", offer='" + offer + '\'' +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean same = ((Offer) obj).toString().equalsIgnoreCase(this.toString());
        return same;
    }
}
