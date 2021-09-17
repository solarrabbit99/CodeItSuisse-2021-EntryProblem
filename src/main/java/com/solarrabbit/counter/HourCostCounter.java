package com.solarrabbit.counter;

import java.time.LocalDateTime;

import com.solarrabbit.file.Rate;

public class HourCostCounter implements UnitCostCounter {
    private final int startHour;
    private final int endHour;
    private final double dayRate;
    private final double nightRate;

    /**
     * Constructs an hour cost counting system with the given hours for the start
     * and end of a day, and the respective minute rates for day and night periods.
     * As day time cannot include midnight, {@code startHour} must come before or
     * the same as {@code endHour}. If {@code startHour == endHour}, there will no
     * day time.
     *
     * </p>
     * Note: The counting system does not take in consideration incomplete minutes
     * into the cost.
     *
     * @param startHour starting hour of a day (inclusive)
     * @param endHour   ending hour of a day (exclusive)
     * @param dayRate   rate per minute in day time
     * @param nightRate rate per minute in night time
     */
    public HourCostCounter(int startHour, int endHour, double dayRate, double nightRate) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.dayRate = dayRate;
        this.nightRate = nightRate;
    }

    public static HourCostCounter of(Rate day, Rate night) {
        int startHour = day.getStartHour();
        int endHour = day.getEndHour();
        int dayRate = day.getValue();
        int nightRate = night.getValue();
        return new HourCostCounter(startHour, endHour, dayRate, nightRate);
    }

    @Override
    public double absoluteCost(LocalDateTime dateTime) {
        final int minute = dateTime.getMinute();
        return minute * getRate(dateTime);
    }

    @Override
    public double fullUnitCost(LocalDateTime dateTime) {
        return 60 * getRate(dateTime);
    }

    private double getRate(LocalDateTime dateTime) {
        return isDay(dateTime) ? dayRate : nightRate;
    }

    private boolean isDay(LocalDateTime dateTime) {
        int hour = dateTime.getHour();
        return startHour <= hour && hour < endHour;
    }

}
