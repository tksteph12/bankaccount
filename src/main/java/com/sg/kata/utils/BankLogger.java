package com.sg.kata.utils;


import org.apache.log4j.Logger;

public abstract class BankLogger {
    public static Logger logger = Logger.getLogger(BankLogger.class);


    public static void logInfo(String message){
        logger.info(message);
    }

    public static void logError(String message){
        logger.error(message);
    }

    public static void logError(String message,Throwable t){
        logger.error(message,t);
    }

}
