package com.solarrabbit;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

import com.solarrabbit.counter.DayCostCounter;
import com.solarrabbit.counter.GeneralCostCounter;
import com.solarrabbit.counter.HourCostCounter;
import com.solarrabbit.counter.HourIntervalCostCounter;
import com.solarrabbit.counter.IntervalCostCounter;
import com.solarrabbit.file.Rate;

public class App implements IntervalCostCounter {
    private final GeneralCostCounter counter;
    private final HourIntervalCostCounter discounter;

    private static LocalDateTime start;
    private static LocalDateTime end;

    /**
     * Constructs an application with respective hour cost counters for both
     * weekdays and weekends, with a discount hour interval of which the cost is not
     * counted.
     *
     * @param weekDayCounter
     * @param weekEndCounter
     * @param discountInterval
     */
    public App(HourCostCounter weekDayCounter, HourCostCounter weekEndCounter, int discountInterval) {
        DayCostCounter dayCostCounter = new DayCostCounter(weekDayCounter, weekEndCounter);
        this.counter = new GeneralCostCounter(dayCostCounter);
        this.discounter = new HourIntervalCostCounter(counter, discountInterval);
    }

    /**
     * Returns an application with a json object as input.
     *
     * @param input a Json object representing the input.
     * @return the App object.
     */
    public static App of(JSONObject input) {
        JSONObject shiftJson = (JSONObject) input.get("shift");
        start = LocalDateTime.parse((String) shiftJson.get("start"));
        end = LocalDateTime.parse((String) shiftJson.get("end"));

        JSONObject roboRateJson = (JSONObject) input.get("roboRate");
        JSONObject standardDayJson = (JSONObject) roboRateJson.get("standardDay");
        JSONObject standardNightJson = (JSONObject) roboRateJson.get("standardNight");
        JSONObject extraDayJson = (JSONObject) roboRateJson.get("extraDay");
        JSONObject extraNightJson = (JSONObject) roboRateJson.get("extraNight");

        Rate standardDay = Rate.fromJsonObject(standardDayJson);
        Rate standardNight = Rate.fromJsonObject(standardNightJson);
        Rate extraDay = Rate.fromJsonObject(extraDayJson);
        Rate extraNight = Rate.fromJsonObject(extraNightJson);

        HourCostCounter weekDayCounter = HourCostCounter.of(standardDay, standardNight);
        HourCostCounter weekEndCounter = HourCostCounter.of(extraDay, extraNight);
        return new App(weekDayCounter, weekEndCounter, 8);
    }

    /**
     * Calculates the cost between two intervals.
     *
     * @param start of the interval
     * @param end   of the interval
     * @return the cost between the two intervals.
     */
    @Override
    public double getCostBetween(LocalDateTime start, LocalDateTime end) {
        return this.counter.getCostBetween(start, end) - this.discounter.getCostBetween(start, end);
    }

    /**
     * Returns the result of the calculation.
     */
    public double run() {
        return getCostBetween(start, end);
    }
}
