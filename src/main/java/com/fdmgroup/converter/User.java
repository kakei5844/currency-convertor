package com.fdmgroup.converter;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a user with a name and a wallet containing currency balances.
 * <p>Provides getters and setters for accessing and modifying the user's name and wallet.
 * 
 * @author [Author]
 * @version [Version]
 */
public class User {
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("wallet")
    private Map<String, Double> wallet;

    /**
	 * Getter method to get the {@code name} class attribute of the
	 * {@code User} object.
	 * 
	 * @return	name	name of the current user
	 * 
	 */
    public String getName() {
        return name;
    }

    /**
	 * Setter method to set the {@code name} class attribute of the 
	 * {@code User} object.
	 * 
	 * @param	name	name of user to set
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * Getter method to get the {@code wallet} class attribute of the
	 * {@code User} object.
	 * 
	 * @return	wallet	wallet of the user containing currency balances
	 * 
	 */
    public Map<String, Double> getWallet() {
        return wallet;
    }

    /**
	 * Setter method to set the {@code wallet} class attribute of the 
	 * {@code User} object.
	 * 
	 * @param	wallet	wallet of the user
	 */
    public void setWallet(Map<String, Double> wallet) {
        this.wallet = wallet;
    }
    
}

