package com.solarrabbit.counter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class DayCostCounterTest {
    private static final LocalDateTime REF_FRIDAY = LocalDate.of(2038, Month.JANUARY, 1).atStartOfDay();

    @Test
    public void testFullUnitCost() {
        final HourCostCounter weekDayCounter = new HourCostCounter(7, 23, 20, 25);
        final HourCostCounter weekEndCounter = new HourCostCounter(7, 23, 30, 35);
        final DayCostCounter counter = new DayCostCounter(weekDayCounter, weekEndCounter);

        assertEquals(31200, counter.fullUnitCost(REF_FRIDAY), 0);
        assertEquals(31200, counter.fullUnitCost(REF_FRIDAY.minusDays(1)), 0);
        assertEquals(31200, counter.fullUnitCost(REF_FRIDAY.minusDays(2)), 0);
        assertEquals(31200, counter.fullUnitCost(REF_FRIDAY.plusDays(3)), 0);

        assertEquals(45600, counter.fullUnitCost(REF_FRIDAY.plusDays(1)), 0);
        assertEquals(45600, counter.fullUnitCost(REF_FRIDAY.plusDays(2)), 0);
    }

    @Test
    public void testAbsoluteCost() {
        final HourCostCounter weekDayCounter = new HourCostCounter(7, 23, 20, 25);
        final HourCostCounter weekEndCounter = new HourCostCounter(7, 23, 30, 35);
        final DayCostCounter counter = new DayCostCounter(weekDayCounter, weekEndCounter);

        assertEquals(0, counter.absoluteCost(REF_FRIDAY), 0);
        assertEquals(750, counter.absoluteCost(REF_FRIDAY.minusDays(1).plusMinutes(30)), 0);
        assertEquals(750, counter.absoluteCost(REF_FRIDAY.minusDays(2).plusMinutes(30)), 0);
        assertEquals(750, counter.absoluteCost(REF_FRIDAY.plusDays(3).plusMinutes(30)), 0);

        assertEquals(1050, counter.absoluteCost(REF_FRIDAY.plusDays(1).plusMinutes(30)), 0);
    }

}
