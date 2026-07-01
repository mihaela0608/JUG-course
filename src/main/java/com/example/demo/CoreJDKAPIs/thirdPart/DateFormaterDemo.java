package com.example.demo.CoreJDKAPIs.thirdPart;

import java.time.LocalDate;

public class DateFormaterDemo {
    public static void main(String[] args) {
        DateFormater dateFormater = new DateFormater(LocalDate.now());

        System.out.println(dateFormater.formatedDate());
        System.out.println(dateFormater.dayOfWeek());
        System.out.println(dateFormater.dayOfMonth());
    }
}
