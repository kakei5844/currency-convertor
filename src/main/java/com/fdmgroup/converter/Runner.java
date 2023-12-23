package com.fdmgroup.converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The main class responsible for running the currency exchange transaction processing application.
 * Reads transactions from a file, executes them using the TransactionProcessor, and updates the users' file.
 * <p> Note: This class assumes that the transactions file is located at "src/main/resources/transactions.txt".
 * 
 * @author Kiki Lam
 * @version 1.0
 */
public class Runner {

    /**
     * The main entry point of the application.
     * Reads transactions from a file, executes them, and updates the users' file.
     */
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();

        TransactionProcessor tp = new TransactionProcessor();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                tp.executeTransaction(line);
                line = reader.readLine();
            }
            tp.updateUsersFile();
            reader.close();
            
        } catch (FileNotFoundException e) {
            logger.fatal(e.getMessage());
        } catch (IOException e) {
            logger.fatal(e.getMessage());
        }

    }
}