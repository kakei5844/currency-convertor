package com.fdmgroup.converter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a currency with its associated properties such as code, alpha code, numeric code, name, and rate.
 * This class provides getters and setters for accessing and modifying the currency attributes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
    @JsonProperty("code")
    private String code;

    @JsonProperty("alphaCode")
    private String alphaCode;

    @JsonProperty("numericCode")
    private String numericCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rate")
    private double rate;


    /**
	 * Getter method to get the {@code code} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return	code    code of the currency
	 */
    public String getCode() {
        return code;
    }
    
    /**
	 * Setter method to set the {@code code} class attribute of the 
	 * {@code Currency} object.
	 * 
	 * @param	code	code of the currency
	 */
    public void setCode(String code) {
        this.code = code;
    }

    /**
	 * Getter method to get the {@code alphaCode} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return	alphaCode	alphaCode of the currency
	 */
    public String getAlphaCode() {
        return alphaCode;
    }

    /**
	 * Setter method to set the {@code alphaCode} class attribute of the 
	 * {@code Currency} object.
	 * 
	 * @param	alphaCode	alphaCode of the currency
	 */
    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    /**
	 * Getter method to get the {@code numericCode} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return  numericCode	numericCode of the currency
	 */
    public String getNumericCode() {
        return numericCode;
    }

    /**
	 * Setter method to set the {@code numericCode} class attribute of the 
	 * {@code Currency} object.
	 * 
	 * @param	numericCode	numericCode of the currency
	 */
    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    /**
	 * Getter method to get the {@code name} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return	name	name of the currency
	 */
    public String getName() {
        return name;
    }

    /**
	 * Setter method to set the {@code name} class attribute of the 
	 * {@code Currency} object.
	 * 
	 * @param	name	name of the currency
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * Getter method to get the {@code rate} class attribute of the
	 * {@code Currency} object.
	 * 
	 * @return	rate	rate of the currency (to usd)
	 */
    public double getRate() {
        return rate;
    }

    /**
	 * Setter method to set the {@code rate} class attribute of the 
	 * {@code Currency} object.
	 * 
	 * @param	rate	rate of the currency
	 */
    public void setRate(double rate) {
        this.rate = rate;
    }
}
