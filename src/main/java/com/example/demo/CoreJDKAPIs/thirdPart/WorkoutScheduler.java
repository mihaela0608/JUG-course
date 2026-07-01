package com.example.demo.CoreJDKAPIs.thirdPart;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class WorkoutScheduler {
    private LocalDate date;

    public WorkoutScheduler(LocalDate date) {
        this.date = date;
    }

    public long daysUntilWorkout(){
        return LocalDate.now().datesUntil(date).count();
    }
}
