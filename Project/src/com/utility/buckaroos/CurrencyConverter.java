package com.utility.buckaroos;

/**
 * This class supports conversion from one type of currency to another.
 * Supported currencies are USD, AUD, BRL, CAD, CNY, EUR, GBP, JPY, INR, CHF,
 * RUB, MXN, and AED.
 * 
 * @author Jordan LeRoux
 * @version 1.0
 */
public class CurrencyConverter {

    private static final double FROMUSDTOAUD = 1.1206;
    private static final double FROMUSDTOBRL = 2.3443;
    private static final double FROMUSDTOCAD = 1.1064;
    private static final double FROMUSDTOCNY = 6.1451;
    private static final double FROMUSDTOEUR = 0.7245;
    private static final double FROMUSDTOGBP = 0.5972;
    private static final double FROMUSDTOJPY = 101.8;
    private static final double FROMUSDTOINR = 61.7575;
    private static final double FROMUSDTOCHF = 0.8803;
    private static final double FROMUSDTORUB = 35.8641;
    private static final double FROMUSDTOMXN = 13.2492;
    private static final double FROMUSDTOAED = 3.673;

    // public CurrencyConverter(Enum<Money> fromCurrency, Enum<Money>
    // toCurrency,
    // double amount) {
    // double conversionRate = getConversionRate(fromCurrency, toCurrency);
    // }

    /**
     * Converts a supported currency to another supported currency
     * 
     * @param fromCurrency The currency to convert from
     * @param toCurrency The currency to convert to
     * @param amount The amount of currency to convert
     * @return The amount of money in the new currency
     */
    public double convertCurrency(Enum<Money> fromCurrency,
            Enum<Money> toCurrency, double amount) {
        double newAmount = 0;
        if (fromCurrency != Money.valueOf("USD")
                && toCurrency != Money.valueOf("USD")) {
            double fromCurrencyInDollars = convertToDollars(fromCurrency,
                    amount);
            newAmount = convertCurrency(Money.USD, toCurrency,
                    fromCurrencyInDollars);
        } else if (fromCurrency != Money.valueOf("USD")
                && toCurrency == Money.valueOf("USD")) {
            newAmount = convertToDollars(fromCurrency, amount);
        } else if (fromCurrency == Money.valueOf("USD")
                && toCurrency != Money.valueOf("USD")) {
            newAmount = convertFromDollars(toCurrency, amount);
        } else if (fromCurrency == Money.valueOf("USD")
                && toCurrency == Money.valueOf("USD")) {
            newAmount = amount;
        }
        return newAmount;
    }

    private double convertToDollars(Enum<Money> fromCurrency, double amount) {
        double valueInDollars = -1;
        double conversionRate = 0;
        if (fromCurrency == Money.valueOf("AUD")) {
            conversionRate = 1 / FROMUSDTOAUD;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("BRL")) {
            conversionRate = 1 / FROMUSDTOBRL;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("CAD")) {
            conversionRate = 1 / FROMUSDTOCAD;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("CNY")) {
            conversionRate = 1 / FROMUSDTOCNY;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("EUR")) {
            conversionRate = 1 / FROMUSDTOEUR;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("GBP")) {
            conversionRate = 1 / FROMUSDTOGBP;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("JPY")) {
            conversionRate = 1 / FROMUSDTOJPY;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("INR")) {
            conversionRate = 1 / FROMUSDTOINR;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("CHF")) {
            conversionRate = 1 / FROMUSDTOCHF;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("RUB")) {
            conversionRate = 1 / FROMUSDTORUB;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("MXN")) {
            conversionRate = 1 / FROMUSDTOMXN;
            valueInDollars = amount * conversionRate;
        } else if (fromCurrency == Money.valueOf("AED")) {
            conversionRate = 1 / FROMUSDTOAED;
            valueInDollars = amount * conversionRate;
        }
        return valueInDollars;
    }

    private double convertFromDollars(Enum<Money> toCurrency, double amount) {
        double newValue = -1;
        double conversionRate = 0;
        if (toCurrency == Money.valueOf("AUD")) {
            conversionRate = FROMUSDTOAUD;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("BRL")) {
            conversionRate = FROMUSDTOBRL;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("CAD")) {
            conversionRate = FROMUSDTOCAD;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("CNY")) {
            conversionRate = FROMUSDTOCNY;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("EUR")) {
            conversionRate = FROMUSDTOEUR;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("GBP")) {
            conversionRate = FROMUSDTOGBP;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("JPY")) {
            conversionRate = FROMUSDTOJPY;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("INR")) {
            conversionRate = FROMUSDTOINR;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("CHF")) {
            conversionRate = FROMUSDTOCHF;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("RUB")) {
            conversionRate = FROMUSDTORUB;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("MXN")) {
            conversionRate = FROMUSDTOMXN;
            newValue = amount * conversionRate;
        } else if (toCurrency == Money.valueOf("AED")) {
            conversionRate = FROMUSDTOAED;
            newValue = amount * conversionRate;
        }
        return newValue;
    }

}
