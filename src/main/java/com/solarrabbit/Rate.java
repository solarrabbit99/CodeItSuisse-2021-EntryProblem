package com.solarrabbit;

import java.time.LocalTime;

import org.json.simple.JSONObject;

public class Rate {
    private final LocalTime start;

    private final LocalTime end;

    private final int value;

    private Rate(LocalTime start, LocalTime end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    public static Rate fromJsonObject(JSONObject obj) {
        LocalTime start = LocalTime.parse((String) obj.get("start"));
        LocalTime end = LocalTime.parse((String) obj.get("end"));
        long value = (long) obj.get("value");
        int v = ((int) value);
        return new Rate(start, end, v);
    }

    public int getValue() {
        return value;
    }

    public int getStartHour() {
        return start.getHour();
    }

    public int getEndHour() {
        return end.getHour();
    }
}
