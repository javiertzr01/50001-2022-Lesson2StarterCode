package com.example.norman_lee.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //TODO 5.4 Write unit tests to check the ExchangeRate class
    @Test
    public void exchangeRateDefaultRate(){
        String defaultExchangeRate = "2.95000";
        assertEquals( new BigDecimal(defaultExchangeRate) , new ExchangeRate().getExchangeRate());
    }

    @Test
    public void checkCalculateAmount(){
        ExchangeRate er = new ExchangeRate();
        assertEquals(true, er.calculateAmount("76") instanceof BigDecimal);
    }

    @Test
    public void emptyStringConstructor(){
        try{
            Utils.checkInvalidInputs("");
        }
        catch(Exception e){
            assertEquals(true, e instanceof NumberFormatException);
        }
    }
}