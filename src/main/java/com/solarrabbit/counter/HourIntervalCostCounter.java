package com.solarrabbit.counter;

import java.time.LocalDateTime;

import com.solarrabbit.util.ObjectUtils;

public class HourIntervalCostCounter implements IntervalCostCounter {
    private final GeneralCostCounter counter;
    private final int interval;

    public HourIntervalCostCounter(GeneralCostCounter counter, int interval) {
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
