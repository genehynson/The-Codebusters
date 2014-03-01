package com.utility.buckaroos;

//Need to include the currency for bangladesh. It's unicode symbol for currency 
//is "\u09F3"

public class CurrencyInformationProvider {

    public String getFullCurrencyName(Enum<Money> currencyAbbrev) {
        String fullNameOfCurrency = "";
        if (currencyAbbrev == Money.valueOf("AUD")) {
            fullNameOfCurrency = "Australia Dollars";
        } else if (currencyAbbrev == Money.valueOf("BRL")) {
            fullNameOfCurrency = "Brazil Reais";
        } else if (currencyAbbrev == Money.valueOf("CAD")) {
            fullNameOfCurrency = "Canada Dollars";
        } else if (currencyAbbrev == Money.valueOf("CNY")) {
            fullNameOfCurrency = "China Yuan Renminbi";
        } else if (currencyAbbrev == Money.valueOf("EUR")) {
            fullNameOfCurrency = "Euros";
        } else if (currencyAbbrev == Money.valueOf("GBP")) {
            fullNameOfCurrency = "United Kingdom Pounds";
        } else if (currencyAbbrev == Money.valueOf("JPY")) {
            fullNameOfCurrency = "Japan Yen";
        } else if (currencyAbbrev == Money.valueOf("INR")) {
            fullNameOfCurrency = "India Rupees";
        } else if (currencyAbbrev == Money.valueOf("CHF")) {
            fullNameOfCurrency = "Switzerland Francs";
        } else if (currencyAbbrev == Money.valueOf("RUB")) {
            fullNameOfCurrency = "Russia Rubles";
        } else if (currencyAbbrev == Money.valueOf("MXN")) {
            fullNameOfCurrency = "Mexico Pesos";
        } else if (currencyAbbrev == Money.valueOf("AED")) {
            fullNameOfCurrency = "United Arab Emirates Dirhams";
        }
        return fullNameOfCurrency;
    }

    public String getSymbolOfCurrency(Enum<Money> currencyAbbrev) {
        // Locale locale = Locale.UK;
        // Currency curr = Currency.getInstance(locale);
        String symbol = "";
        if (currencyAbbrev == Money.valueOf("AUD")) {
            symbol = "AU$";
        } else if (currencyAbbrev == Money.valueOf("BRL")) {
            symbol = "R$";
        } else if (currencyAbbrev == Money.valueOf("CAD")) {
            symbol = "C$";
        } else if (currencyAbbrev == Money.valueOf("CNY")) {
            symbol = "C¥";
        } else if (currencyAbbrev == Money.valueOf("EUR")) {
            symbol = "€";
        } else if (currencyAbbrev == Money.valueOf("GBP")) {
            symbol = "£";
        } else if (currencyAbbrev == Money.valueOf("JPY")) {
            symbol = "J¥";
        } else if (currencyAbbrev == Money.valueOf("INR")) {
            symbol = "INR";
        } else if (currencyAbbrev == Money.valueOf("CHF")) {
            symbol = "CHF";
        } else if (currencyAbbrev == Money.valueOf("RUB")) {
            symbol = "руб";
        } else if (currencyAbbrev == Money.valueOf("MXN")) {
            symbol = "MEX$";
        } else if (currencyAbbrev == Money.valueOf("AED")) {
            symbol = "AED";
        }
        return symbol;
    }

}
