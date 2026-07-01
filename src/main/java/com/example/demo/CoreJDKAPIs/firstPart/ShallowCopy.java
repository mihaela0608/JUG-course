package com.example.demo.CoreJDKAPIs.firstPart;

public class ShallowCopy {
    public static void main(String[] args) {
        Book first = new Book("C&P", "F.M.D", new Publisher("Ivan"), 432);
        Book second = first.clone();
        second.setPublisher(new Publisher("Geri"));
        System.out.println(first);
        System.out.println(second);

    }

}
