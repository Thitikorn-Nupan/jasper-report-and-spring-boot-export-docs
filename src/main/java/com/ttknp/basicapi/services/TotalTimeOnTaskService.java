package com.ttknp.basicapi.services;

public class TotalTimeOnTaskService {

    public static long timeStart() {
        long startTime = System.nanoTime();
        return startTime;
    }

    public static long timeEnd(long startTime) {
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        long durationInMs = duration / 1000000;
        return durationInMs;
    }
}
