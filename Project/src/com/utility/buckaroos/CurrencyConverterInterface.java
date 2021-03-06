package com.utility.buckaroos;

/**
 * This class defines an interface for the Currency Converter object.
 *
 * @author Jordan LeRoux
 * @version 1.0
 */
public interface CurrencyConverterInterface {

    /**
     * Converts a supported currency to another supported currency.
     *
     * @param fromCurrency The currency to convert from
     * @param toCurrency The currency to convert to
     * @param amount The amount of currency to convert
     * @return The amount of money in the new currency
     */
    double convertCurrency(Enum<Money> fromCurrency,
            Enum<Money> toCurrency, double amount);

}
