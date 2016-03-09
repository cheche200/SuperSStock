package com.supersimplestock.exception;

import org.apache.log4j.Logger;


@SuppressWarnings("serial")
public class StockException extends Exception {
	private static Logger log = Logger.getLogger(StockException.class);
	public StockException(String message) {
        super(message);
        log.error(message);
    }
}
