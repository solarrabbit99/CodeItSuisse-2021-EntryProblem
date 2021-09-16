package com.solarrabbit.counter;

import java.time.LocalDateTime;

/**
 * Encapsulates a cost counter across a period of time. Its implementing classes
 * are free to decide the pattern/rules for which the cost is calculated.
 *
 * @see GeneralCostCounter
 */
public interface IntervalCostCounter {

    /**
     * Returns the cost between two arbitrary points in time.
     *
     * @param start of the interval
     * @param end   of the interval
     * @return total cost for the interval
     */
    double getCostBetween(LocalDateTime start, LocalDateTime end);

}
