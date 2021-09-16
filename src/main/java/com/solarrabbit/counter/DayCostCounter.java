package com.solarrabbit.counter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DayCostCounter implements UnitCostCounter {
    private final HourCostCounter weekDayCounter;
    private final HourCostCounter weekEndCounter;

    public DayCostCounter(HourCostCounter weekDayCounter, HourCostCounter weekEndCounter) {
        this.weekDayCounter = weekDayCounter;
        this.weekEndCounter = weekEndCounter;
    }

    @Override
    public double absoluteCost(LocalDateTime dateTime) {
        final HourCostCounter hourCostCounter = getHourCostCounter(dateTime);
        final int endHour = dateTime.getHour();
        double totalCost = 0;
        for (int i = 0; i < endHour; i++) {
            totalCost += hourCostCounter.fullUnitCost(dateTime.withHour(i));
        }
        totalCost += hourCostCounter.absoluteCost(dateTime);
        return totalCost;
    }

    @Override
    public double fullUnitCost(LocalDateTime dateTime) {
        final HourCostCounter hourCostCounter = getHourCostCounter(dateTime);
        double totalCost = 0;
        for (int i = 0; i < 24; i++) {
            totalCost += hourCostCounter.fullUnitCost(dateTime.withHour(i));
        }
        return totalCost;
    }

    private HourCostCounter getHourCostCounter(LocalDateTime dateTime) {
        return isWeekEnd(dateTime) ? this.weekEndCounter : this.weekDayCounter;
    }

    private boolean isWeekEnd(LocalDateTime dateTime) {
        DayOfWeek day = dateTime.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

}
