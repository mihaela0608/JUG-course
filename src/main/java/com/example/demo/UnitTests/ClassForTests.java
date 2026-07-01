package com.example.demo.UnitTests;

import java.util.List;


public class ClassForTests {


    public int add(int a, int b) {
        return a + b;
    }


    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }


    public boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


    public int findMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty");
        }
        int max = numbers.get(0);
        for (int n : numbers) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }


    public String reverseString(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }


    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        String cleaned = input.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }


    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }


    public String categorizeAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        } else if (age < 13) {
            return "child";
        } else if (age < 20) {
            return "teen";
        } else if (age < 65) {
            return "adult";
        } else {
            return "senior";
        }
    }
}
