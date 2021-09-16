package com.solarrabbit.util;

public class ObjectUtils {

    public static <T extends Comparable<? super T>> T min(T first, T second) {
        return first.compareTo(second) <= 0 ? first : second;
    }

}
