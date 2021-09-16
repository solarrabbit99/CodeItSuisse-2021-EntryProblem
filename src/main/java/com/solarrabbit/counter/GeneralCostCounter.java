package com.solarrabbit.counter;

import java.time.LocalDateTime;

public class GeneralCostCounter implements IntervalCostCounter {
    private final DayCostCounter dayCostCounter;

    public GeneralCostCounter(DayCostCounter dayCostCounter) {
        this.dayCostCounter = dayCostCounter;
    }

    @Override
    public double getCostBetween(LocalDateTime start, LocalDateTime end) {
        if (isSameDay(start, end))
            return this.dayCostCounter.absoluteCost(end) - this.dayCostCounter.absoluteCost(start);

        final LocalDateTime loopEndDay = floorDay(end);
        LocalDateTime loopingDay = ceilDay(start);
        double totalCost = this.dayCostCounter.fullUnitCost(start) - this.dayCostCounter.absoluteCost(start);
        while (!loopingDay.equals(loopEndDay)) {
            totalCost += this.dayCostCounter.fullUnitCost(loopingDay);
            loopingDay = loopingDay.plusDays(1);
        }
        totalCost += this.dayCostCounter.absoluteCost(end);

        return totalCost;
    }

    private LocalDateTime ceilDay(LocalDateTime dateTime) {
        return floorDay(dateTime).plusDays(1);
    }

    private LocalDateTime floorDay(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atStartOfDay();
    }

    private boolean isSameDay(LocalDateTime first, LocalDateTime second) {
        return floorDay(first).equals(floorDay(second));
    }

}
