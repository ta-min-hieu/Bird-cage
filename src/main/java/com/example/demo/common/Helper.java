package com.example.demo.common;

public class Helper {
    public static String processStringSearch(String input) {
        if(input != null) {
            input = input.trim();
            if(input.equals(""))
                input = null;
        }
        return input;
    }
}
