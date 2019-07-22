package com.bipinayetra.convoy.api;

public class Sort {
    public static String pickupDate = "pickupDate";
    public static String dropoffDate = "dropoffDate";
    public static String price = "price";
    public static String origin = "origin";
    public static String destination = "destination";
    public static String miles = "miles";

    public static String type = pickupDate;
    public static int offset;
    public static int limit;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Sort.type = type;
    }

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        Sort.offset = offset;
    }

    public static int getLimit() {
        return limit;
    }

    public static void setLimit(int limit) {
        Sort.limit = limit;
    }
}
