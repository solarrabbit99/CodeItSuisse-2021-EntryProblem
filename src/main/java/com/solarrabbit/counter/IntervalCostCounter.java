package com.solarrabbit.counter;

import java.time.LocalDateTime;

/**
 * Encapsulates a cost counter across a period of time. Its implementing classes
 * are free to decide the pattern/rules for which the cost is calculated.
 *
 * @see GeneralCostCounter
 */
public interface IntervalCostCounter {

    double getCostBetween(LocalDateTime start, LocalDateTime end);

}
