package com.solarrabbit.counter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class GeneralCostCounterTest {
    private static final LocalDateTime REF_FRIDAY = LocalDate.of(2038, Month.JANUARY, 1).atStartOfDay();

    @Test
    public void testGeneral() {
        final HourCostCounter weekDayCounter = new HourCostCounter(7, 23, 20, 25);
        final HourCostCounter weekEndCounter = new HourCostCounter(7, 23, 30, 35);
        final DayCostCounter dayCostCounter = new DayCostCounter(weekDayCounter, weekEndCounter);
        final GeneralCostCounter counter = new GeneralCostCounter(dayCostCounter);

        double cost = counter.getCostBetween(REF_FRIDAY.withHour(20).withMinute(15),
                REF_FRIDAY.plusDays(1).withHour(4).withMinute(15));
        assertEquals(13725, cost, 0);
    }

}
