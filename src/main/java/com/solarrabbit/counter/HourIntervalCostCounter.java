package com.solarrabbit.counter;

import java.time.LocalDateTime;

import com.solarrabbit.util.ObjectUtils;

/**
 * Encapsulates an interval cost counter that only counts the cost of paid hours
 * in intervals.
 */
public class HourIntervalCostCounter implements IntervalCostCounter {
    private final IntervalCostCounter counter;
    private final int interval;

    /**
     * Constructs an interval counter with an underlying interval cost counter and
     * an interval. If {@code interval == 0}, the counter's
     * {@link #getCostBetween(LocalDateTime, LocalDateTime)} method will give the
     * same output as the underlying interval cost counter.
     *
     * @param counter  underlying interval cost used to calculate the cost of a paid
     *                 hour
     * @param interval between the end of the previous paid hour and the beginning
     *                 of the next paid hour
     */
    public HourIntervalCostCounter(IntervalCostCounter counter, int interval) {
        this.counter = counter;
        this.interval = interval;
    }

    @Override
    public double getCostBetween(LocalDateTime start, LocalDateTime end) {
        LocalDateTime loopingHour = start.plusHours(interval);
        double totalCost = 0;
        while (loopingHour.compareTo(end) < 0) {
            final LocalDateTime plusOne = loopingHour.plusHours(1);
            LocalDateTime loopEnd = ObjectUtils.min(plusOne, end);
            totalCost += this.counter.getCostBetween(loopingHour, loopEnd);

            loopingHour = plusOne.plusHours(interval);
        }
        return totalCost;
    }

}
