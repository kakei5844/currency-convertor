package com.fdmgroup.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Class responsible for processing currency transactions.
 * <p>Allows executing transactions based on the provided transaction information.
 * <p>Updates the user file with the current user list.
 * <p>Uses a converter for currency conversion.
 * 
 * @author Kiki Lam
 * @version 1.0
 * @see com.fdmgroup.converter.Converter
 * @see com.fdmgroup.converter.User
 */
public class TransactionProcessor {
    /** The logger used for logging transaction information. */
    Logger logger;

    /** The list of users. */
    private List<User> userList;

    /** The converter used for currency conversion. */    
    private Converter converter;

    /**
     * Constructs a TransactionProcessor object.
     * Initializes the converter, user list, and logger.
     */
    public TransactionProcessor() {
        createConverter();
        createUserList();
        logger = LogManager.getLogger();
    }

    /**
     * Executes a transaction based on the provided transaction information.
     *
     * @param   transactionInfo the transaction information to process
     */
    public void executeTransaction(String transactionInfo) {
        // transactionInfo: "{name} {from} {to} {amount}"
        String[] transactionInfoArray = transactionInfo.split(" ");
        String userName = transactionInfoArray[0];
        String from = transactionInfoArray[1].toLowerCase();
        String to = transactionInfoArray[2].toLowerCase();
        double fromAmount = Double.parseDouble(transactionInfoArray[3]);

         try {
            double convertedAmount = converter.convert(from, to, fromAmount);

            User user = getUserByUserName(userName);
            deductAmountFromUser(user, from, fromAmount);
            addAmountToUser(user, to, convertedAmount);

            logger.info(transactionInfo + " > succeeded");
            
         } catch (InvalidCurrencyException | InvalidAmountException | UnknownUserException e) {
            logger.error(transactionInfo + " > skipped: " + e.getMessage());
         } catch (SameCurrencyException | InsufficientBalanceException e) {
            logger.warn(transactionInfo + " > skipped: " + e.getMessage());
         }
    }

    /**
     * Updates the user file with the current user list.
     */
    public void updateUsersFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            File jsonFile = new File("src/main/resources/users_new.json");
            objectMapper.writeValue(jsonFile, userList);
        } catch (IOException e) {
            logger.fatal(e.getMessage());
        }
    }

    /**
     * Retrieves the user with the given user name.
     * 
     * @param   userName the user name to search for
     * @return  user the user with the given user name
     * @throws  UnknownUserException if no user with the given user name is found
     */
    private User getUserByUserName(String userName) throws UnknownUserException {
        User user = null;
        for (User u : userList) {
            if (u.getName().equals(userName)) {
                user = u;
                break;
            }
        }
        if (user == null) throw new UnknownUserException("No such user (" + userName + ")");
        return user;
    }

    /**
     * Creates the converter object.
     */
    private void createConverter() {
        converter = new Converter();
    }

    /**
     * Creates the user list from the user file.
     */
    private void createUserList() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File jsonFile = new File("src/main/resources/users.json");
            userList = objectMapper.readValue(jsonFile, new TypeReference<List<User>>() {});

        } catch (FileNotFoundException e) {
            logger.fatal(e.getMessage());
        } catch (IOException e) {
            logger.fatal(e.getMessage());
        }
    }

    /**
     * Deducts the specified amount from the user's wallet.
     * 
     * @param   user the user
     * @param   from the currency to deduct from
     * @param   fromAmount the amount to deduct
     * @throws  InsufficientBalanceException if the user has insufficient balance in the specified currency
     */
    private void deductAmountFromUser(User user, String from, double fromAmount) throws InsufficientBalanceException {
        if (user.getWallet().getOrDefault(from, 0.0) < fromAmount) {
            throw new InsufficientBalanceException("Insufficient balance in " + from);
        } else {
            user.getWallet().put(from, user.getWallet().get(from) - fromAmount);
        }
    }

    /**
     * Adds the specified amount to the user's wallet.
     * 
     * @param   user the user
     * @param   to the currency to add to
     * @param   convertedAmount the amount to add
     */
    private void addAmountToUser(User user, String to, double convertedAmount) {
        user.getWallet().put(to, user.getWallet().getOrDefault(to, 0.0) + convertedAmount);
    }
    
}