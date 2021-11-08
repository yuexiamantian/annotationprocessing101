package com.hannesdorfmann.annotationprocessing101.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtil {
    public static String readConsole() throws IOException {
        System.out.println("What do you like in orange/apple/banana?");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferRead.readLine();
        return s;
    }
}
