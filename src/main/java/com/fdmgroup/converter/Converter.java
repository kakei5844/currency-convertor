package com.fdmgroup.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a currency converter that converts amounts between different currencies.
 * <p>Provides a method to convert an amount from one currency to another.
 * <p>Internally uses a rates map to store the conversion rates between currencies.
 * 
 * <p>Note: This class assumes that the rates map is loaded from a JSON file named "fx_rates.json"
 * located in the "src/main/resources" directory.
 * 
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class Converter {
    Logger logger;
    Map<String, Double> ratesMap;

    /**
     * Constructs a Converter object and initializes the logger and the rates map.
     */
    public Converter() {
        logger = LogManager.getLogger();
        createRatesMap(); 
    }

    /**
     * Converts an amount from one currency to another.
     * 
     * @param   from the currency to convert from
     * @param   to the currency to convert to
     * @param   fromAmount the amount to convert
     * @return  convertedAmount the converted amount
     * @throws InvalidCurrencyException if a currency not existing in the ratesMap is provided
     * @throws InvalidAmountException if a negative amount is provided
     * @throws SameCurrencyException if the same currency is provided for conversion
     */
    public double convert(String from, String to, double fromAmount) throws InvalidCurrencyException, InvalidAmountException, SameCurrencyException {

        isValidCurrency(from);
        isValidCurrency(to);
        isValidAmount(fromAmount);

        if (from.equals(to)) throw new SameCurrencyException("Same currency provided (" + from + ")");

        Double fromRate = ratesMap.get(from);
        Double toRate = ratesMap.get(to);
        return Math.round((fromAmount / fromRate * toRate) * 100.0) / 100.0; // rounded off to 2 decimal places
    }

    private void createRatesMap() {
        Map<String, Currency> currenciesMap = createCurrenciesMap();
        ratesMap = new HashMap<>();
        for (Map.Entry<String, Currency> entry : currenciesMap.entrySet()) {
            ratesMap.put(entry.getKey(), entry.getValue().getRate());
        }
        ratesMap.put("usd", 1.0);
    }

     private Map<String, Currency> createCurrenciesMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Currency> currenciesMap = null;

        try {
			File jsonFile = new File("src/main/resources/fx_rates.json");
			currenciesMap = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Currency>>() {});
            return currenciesMap;
		} catch (FileNotFoundException e) {
			logger.fatal(e.getMessage());
		} catch (IOException e) {
            logger.fatal(e.getMessage());
        }
        return currenciesMap;
    }


    private boolean isValidCurrency(String currency) throws InvalidCurrencyException {
        if (!ratesMap.containsKey(currency)) { 
			throw new InvalidCurrencyException("Currency entered (" + currency + ") is invalid");
		}
		return true;
    }

    private boolean isValidAmount(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount entered (" + amount + ") is invalid");
        }
        return true;
    }


}
