package com.utility.buckaroos;

/**
 * This class defines an interface for the Currency Information object.
 *
 * @author Jordan LeRoux
 * @version 1.0
 */
public interface CurrencyInformationInterface {

    /**
     * Returns the full name of currency when provided with the
     * currency's enum.
     *
     * @param currencyAbbrev The abbreviation of the currency
     * @return The full name of the currency
     */
    String getFullCurrencyName(Enum<Money> currencyAbbrev);

    /**
     * Returns the symbol of the currency when provided with the
     * currency's enum.
     *
     * @param currencyAbbrev The abbreviation of the currency
     * @return The symbol of the currency
     */
    String getSymbolOfCurrency(Enum<Money> currencyAbbrev);

}
