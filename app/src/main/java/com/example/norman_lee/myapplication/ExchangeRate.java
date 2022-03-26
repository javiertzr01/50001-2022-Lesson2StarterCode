package com.example.norman_lee.myapplication;

import org.xml.sax.ext.DeclHandler;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ExchangeRate {

    private BigDecimal exchangeRate;
    private static String defaultRate = "2.95000";
    private static final int DEFAULT_PRECISION = 5;
    private int precision = DEFAULT_PRECISION;
    private MathContext mathContext;


    ExchangeRate(){
        exchangeRate = new BigDecimal(defaultRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String exchangeRate){
        this.exchangeRate = new BigDecimal(exchangeRate);
        instantiateMathContext(DEFAULT_PRECISION);
    }

    ExchangeRate(String home, String foreign) {

        instantiateMathContext(DEFAULT_PRECISION);
        //TODO 3.13a The constructor initializes exchangeRate by calculating the exchangeRate
        if (home == null || foreign == null) {
            exchangeRate = new BigDecimal(defaultRate);
        }
        else{
            BigDecimal foreign_bd = new BigDecimal(foreign);
            BigDecimal home_bd = new BigDecimal(home);
            exchangeRate = foreign_bd.divide(home_bd, mathContext);
        }
    }

    BigDecimal getExchangeRate(){
        return exchangeRate;
    }

    BigDecimal calculateAmount(String foreign){
        //TODO 2.5a complete this method to return the amount
        BigDecimal original = new BigDecimal(foreign);
        BigDecimal result_noRounding = original.multiply(exchangeRate);
        String result_noRounding_string = result_noRounding.toString();
        precision = 0;
        for (int i = 0; i < result_noRounding_string.length(); i++) {
            if (result_noRounding_string.charAt(i) != '.') {
                precision += 1;
            } else {
                precision += 2;
                break;
            }
        }
        instantiateMathContext(precision);
        BigDecimal result = original.multiply(exchangeRate, mathContext);
        return result;
    }

    void setPrecision(int precision){
        this.precision = precision;
        instantiateMathContext(precision);
    }

    private void instantiateMathContext(int precision){
        mathContext = new MathContext(precision, RoundingMode.HALF_UP);
    }
}
