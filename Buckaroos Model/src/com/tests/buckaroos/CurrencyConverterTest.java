package com.tests.buckaroos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.utility.buckaroos.CurrencyConverter;
import com.utility.buckaroos.CurrencyConverterInterface;
import com.utility.buckaroos.Money;

public class CurrencyConverterTest {

    private CurrencyConverterInterface cc;

    @Before
    public void setup() {
        cc = new CurrencyConverter();
    }

    @Test
    public void convertUSDToGBP() {
        // FROMUSDTOGBP = 0.5972;
        assertEquals(298.6, cc.convertCurrency(Money.USD, Money.GBP, 500), .001);
        assertEquals(0, cc.convertCurrency(Money.USD, Money.GBP, 0), .001);
        assertEquals(-298.6, cc.convertCurrency(Money.USD, Money.GBP, -500),
                .001);
    }

    @Test
    public void convertAUDToEUR() {
        // FROMUSDTOAUD = 1.1206;
        // FROMUSDTOEUR = 0.7245;
        assertEquals(323.26, cc.convertCurrency(Money.AUD, Money.EUR, 500), .01);
        assertEquals(0, cc.convertCurrency(Money.AUD, Money.EUR, 0), .01);
        assertEquals(-323.26, cc.convertCurrency(Money.AUD, Money.EUR, -500),
                .01);
    }

    @Test
    public void convertBRLToUSD() {
        // FROMUSDTOBRL = 2.3443;
        assertEquals(213.28, cc.convertCurrency(Money.BRL, Money.USD, 500), .01);
        assertEquals(0, cc.convertCurrency(Money.BRL, Money.USD, 0), .01);
        assertEquals(-213.28, cc.convertCurrency(Money.BRL, Money.USD, -500),
                .01);
    }

    @Test
    public void convertUSDToMXN() {
        // FROMUSDTOMXN = 13.2492
        assertEquals(6624.6, cc.convertCurrency(Money.USD, Money.MXN, 500), .01);
        assertEquals(0, cc.convertCurrency(Money.USD, Money.MXN, 0), .01);
        assertEquals(-6624.6, cc.convertCurrency(Money.USD, Money.MXN, -500),
                .01);
    }

    @Test
    public void convertRUBToRUB() {
        assertEquals(500, cc.convertCurrency(Money.RUB, Money.RUB, 500), .01);
        assertEquals(0, cc.convertCurrency(Money.RUB, Money.RUB, 0), .01);
        assertEquals(-500, cc.convertCurrency(Money.RUB, Money.RUB, -500), .01);
    }

    @Test
    public void convertUSDToUSD() {
        assertEquals(500, cc.convertCurrency(Money.USD, Money.USD, 500), .01);
        assertEquals(0, cc.convertCurrency(Money.USD, Money.USD, 0), .01);
        assertEquals(-500, cc.convertCurrency(Money.USD, Money.USD, -500), .01);
    }
}