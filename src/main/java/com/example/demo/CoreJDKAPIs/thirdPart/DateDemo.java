package com.example.demo.CoreJDKAPIs.thirdPart;

import java.time.LocalDate;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2026, 9, 27);
        WorkoutScheduler workoutScheduler = new WorkoutScheduler(date);

        System.out.println(workoutScheduler.daysUntilWorkout());
    }
}
