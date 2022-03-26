package com.example.norman_lee.myapplication;

public class Utils {
    static void checkInvalidInputs(String value) {
        try {
            double doubleValue = Double.valueOf(value);
            if (doubleValue <= 0){
                throw new IllegalArgumentException();
            }
        }
        catch(Exception e) {
            throw new NumberFormatException();
        }
    }
}
