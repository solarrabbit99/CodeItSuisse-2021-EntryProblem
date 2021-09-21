package com.solarrabbit.file;

import java.time.LocalTime;

import org.json.simple.JSONObject;

/**
 * Represents the roborate.
 */
public class Rate {
    private final LocalTime start;
    private final LocalTime end;
    private final int value;

    /**
     * Constructs a rate with the time frame of which the rate applies and the value
     * of the rate.
     *
     * @param start start time of the rate counting.
     * @param end   end time of the rate counting.
     * @param value pay per minute.
     */
    private Rate(LocalTime start, LocalTime end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    /**
     * Constructs a Rate object from Json object.
     */
    public static Rate fromJsonObject(JSONObject obj) {
        LocalTime start = LocalTime.parse((String) obj.get("start"));
        LocalTime end = LocalTime.parse((String) obj.get("end"));
        long value = (long) obj.get("value");
        int v = ((int) value);
        return new Rate(start, end, v);
    }

    /**
     * Returns the pay per minute of the rate.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the starting hour of the rate as an integer.
     */
    public int getStartHour() {
        return start.getHour();
    }

    /**
     * Returns the ending hour of the rate as an integer.
     */
    public int getEndHour() {
        return end.getHour();
    }

}
