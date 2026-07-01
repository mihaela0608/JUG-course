package com.example.demo.CoreJDKAPIs.firstPart;

import java.io.IOException;

public class ProcessDemo {
    public static void main(String[] args) throws IOException {
        Process processBuilder = new ProcessBuilder("notepad.exe").start();
    }
}
