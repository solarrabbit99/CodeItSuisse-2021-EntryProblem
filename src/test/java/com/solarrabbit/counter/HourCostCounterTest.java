package com.solarrabbit.counter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class HourCostCounterTest {
    private static final LocalDateTime REF_FRIDAY = LocalDate.of(2038, Month.JANUARY, 1).atStartOfDay();

    @Test
    public void testFullUnitCost() {
        final HourCostCounter counter = new HourCostCounter(7, 23, 20, 25);

        assertEquals(1200, counter.fullUnitCost(REF_FRIDAY.withHour(7)), 0);
        assertEquals(1200, counter.fullUnitCost(REF_FRIDAY.withHour(9)), 0);
        assertEquals(1200, counter.fullUnitCost(REF_FRIDAY.withHour(22)), 0);

        assertEquals(1500, counter.fullUnitCost(REF_FRIDAY), 0);
        assertEquals(1500, counter.fullUnitCost(REF_FRIDAY.withHour(6)), 0);
        assertEquals(1500, counter.fullUnitCost(REF_FRIDAY.withHour(23)), 0);
    }

    @Test
    public void testAbsoluteCost() {
        final HourCostCounter counter = new HourCostCounter(7, 23, 20, 25);

        assertEquals(0, counter.absoluteCost(REF_FRIDAY), 0);
        assertEquals(25, counter.absoluteCost(REF_FRIDAY.withMinute(1)), 0);
        assertEquals(600, counter.absoluteCost(REF_FRIDAY.withHour(9).withMinute(30)), 0);
    }

}
