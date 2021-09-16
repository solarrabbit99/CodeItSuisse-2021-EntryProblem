package com.solarrabbit.counter;

import java.time.LocalDateTime;

/**
 * Encapsulates a cost counter for a certain unit of time. Its implementing
 * classes should assume a rate that is independent of the starting time.
 *
 * @see HourCostCounter
 * @see DayCostCounter
 */
public interface UnitCostCounter {

    /**
     * Returns the total cost since the beginning of the implementing unit of time.
     *
     * @param dateTime ending dateTime of the period (excluding)
     * @return total cost
     */
    double absoluteCost(LocalDateTime dateTime);

    /**
     * Returns the total cost for the full implementing unit of time.
     *
     * @param dateTime in which this unit includes
     * @return full cost
     */
    double fullUnitCost(LocalDateTime dateTime);

}
