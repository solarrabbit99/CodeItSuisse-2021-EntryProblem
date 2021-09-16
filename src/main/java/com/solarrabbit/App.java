package com.solarrabbit;

import java.time.LocalDateTime;

import com.solarrabbit.counter.DayCostCounter;
import com.solarrabbit.counter.GeneralCostCounter;
import com.solarrabbit.counter.HourCostCounter;
import com.solarrabbit.counter.HourIntervalCostCounter;
import com.solarrabbit.counter.IntervalCostCounter;

public class App implements IntervalCostCounter {
    private final GeneralCostCounter counter;
    private final HourIntervalCostCounter discounter;

    public App(HourCostCounter weekDayCounter, HourCostCounter weekEndCounter, int discountInterval) {
        DayCostCounter dayCostCounter = new DayCostCounter(weekDayCounter, weekEndCounter);
        this.counter = new GeneralCostCounter(dayCostCounter);
        this.discounter = new HourIntervalCostCounter(counter, discountInterval);
    }

    @Override
    public double getCostBetween(LocalDateTime start, LocalDateTime end) {
        return this.counter.getCostBetween(start, end) - this.discounter.getCostBetween(start, end);
    }

    public static void main(String[] args) {

    }

}
