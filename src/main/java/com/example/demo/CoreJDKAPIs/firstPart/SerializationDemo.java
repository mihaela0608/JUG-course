package com.example.demo.CoreJDKAPIs.firstPart;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SerializationDemo {
    public static void main(String[] args){

        //With one object
        Person person = new Person("Iva");
        String fileName = "demo.txt";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Person newPerson = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            newPerson = (Person) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(newPerson);


        //With object with List
        Users users = new Users(Arrays.asList(new Person("Geri"), new Person("Ivana")));
        String secondFileName = "secondDemo.txt";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(secondFileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Users newUsers = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(secondFileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            newUsers = (Users) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(newUsers);
    }
}
