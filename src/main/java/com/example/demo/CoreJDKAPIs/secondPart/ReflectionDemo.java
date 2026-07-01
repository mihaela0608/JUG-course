package com.example.demo.CoreJDKAPIs.secondPart;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

public class ReflectionDemo {
    public static void main(String[] args) {
        Person person = new Person("Iva", 15);
        reflection(person);
    }
    
    public static void reflection(Object object){
        Stream<Field> fields = Arrays.stream(object.getClass().getDeclaredFields());
        Stream<Constructor<?>> constructors = Arrays.stream(object.getClass().getConstructors());
        fields.forEach(System.out::println);
        constructors.forEach(System.out::println);
    }
}
