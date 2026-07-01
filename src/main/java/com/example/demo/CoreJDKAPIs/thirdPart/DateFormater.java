package com.example.demo.CoreJDKAPIs.thirdPart;

import java.time.LocalDate;

public class DateFormater {
    private LocalDate date;

    public DateFormater(LocalDate date){
        this.date = date;
    }

    public String formatedDate(){
        String formatedDate = "";
        formatedDate+=date.getDayOfMonth() + " ";
        formatedDate+=date.getMonth().name() + " ";
        formatedDate+=date.getYear();

        return formatedDate;
    }

    public String dayOfWeek(){
        return date.getDayOfWeek().name();
    }

    public String dayOfMonth(){
        return date.getMonth().name() + " " + date.getDayOfMonth();
    }
}
