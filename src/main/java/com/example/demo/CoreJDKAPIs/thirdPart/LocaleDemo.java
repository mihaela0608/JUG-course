package com.example.demo.CoreJDKAPIs.thirdPart;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleDemo {
    public static void main(String[] args) {
        Locale usLocale = new Locale("en", "US");
        Locale germanLocale = new Locale("de", "DE");

        long number = 123456789L;

        System.out.println(NumberFormat.getInstance(usLocale).format(number));
        System.out.println(NumberFormat.getInstance(germanLocale).format(number));

        Date date = new Date();

        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, usLocale).format(date));
        System.out.println(DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, germanLocale).format(date));
    }
}
