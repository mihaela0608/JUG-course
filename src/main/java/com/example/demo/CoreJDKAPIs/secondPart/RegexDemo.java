package com.example.demo.CoreJDKAPIs.secondPart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^\\d+A");

        Matcher matcher = pattern.matcher("3A");
        System.out.println(matcher.find());
    }
}
