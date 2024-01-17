package com.bu3.skeleton.util;

import java.util.concurrent.TimeUnit;

public class TimeUnitResponse {

    public static long currentTimeSeconds() {
        long currentTimeMillis = System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis);
    }
}
