package com.danielbenami.dragontail.converter;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateTimeConverter {

    private DateTimeConverter() {
    }

    public static LocalDateTime convertUnixTimestamp(long unixTimestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTimestamp), ZoneId.systemDefault());
    }
}