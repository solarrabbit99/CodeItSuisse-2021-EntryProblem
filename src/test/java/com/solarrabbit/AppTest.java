package com.solarrabbit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import com.solarrabbit.counter.HourCostCounter;

import org.junit.Test;

public class AppTest {
    private final HourCostCounter weekDayCounter = new HourCostCounter(7, 23, 20, 25);
    private final HourCostCounter weekEndCounter = new HourCostCounter(7, 23, 30, 35);
    private final App app = new App(weekDayCounter, weekEndCounter, 8);

    @Test
    public void appTest1() {
        LocalDateTime start = LocalDateTime.of(2038, Month.JANUARY, 1, 20, 15);
        LocalDateTime end = LocalDateTime.of(2038, Month.JANUARY, 2, 8, 15);
        assertEquals(19650, app.getCostBetween(start, end), 0);
    }

    @Test
    public void appTest2() {
        LocalDateTime start = LocalDateTime.of(2038, Month.JANUARY, 11, 7, 00);
        LocalDateTime end = LocalDateTime.of(2038, Month.JANUARY, 17, 19, 0);
        assertEquals(202200, app.getCostBetween(start, end), 0);
    }

    @Test
    public void appTest3() {
        LocalDateTime start = LocalDateTime.of(2038, Month.JANUARY, 1, 20, 15);
        LocalDateTime end = LocalDateTime.of(2038, Month.JANUARY, 2, 4, 16);
        assertEquals(13725, app.getCostBetween(start, end), 0);
    }

    @Test
    public void appTest4() {
        LocalDateTime start = LocalDateTime.of(2038, Month.JANUARY, 1, 20, 15);
        LocalDateTime end = LocalDateTime.of(2038, Month.JANUARY, 2, 5, 16);
        assertEquals(13760, app.getCostBetween(start, end), 0);
    }

}
