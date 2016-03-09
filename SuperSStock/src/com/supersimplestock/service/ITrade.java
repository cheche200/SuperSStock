package com.supersimplestock.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.supersimplestock.exception.StockException;
import com.supersimplestock.model.*;
/**
 * <h1>Itrade</h1>
 * 
 * The ITrade interface defines the methods for the business logic for the Trades.
 *
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */
public interface ITrade {
	/**
	 * This method is used to calculate the GBCE All Share Index using the
	 * geometric mean of prices for all stocks.
	 * 
	 * @param list StockBean list.
	 * @return the GBCE All Share Index, in BigDecimal
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */
	public abstract BigDecimal getAllShareIndex(List<StockBean> list) throws StockException;
	/**
	 * This method is used to calculate the price of the Stocks, based on the
	 * trades made in the last 15 minutes.
	 * 
	 * @param list TradeBean list
	 * @param time back to calculate the Trades (Calendar format).
	 * @param stock the StockBean on which the calculation is performed.
	 * @return the Stock price based on the trades made in the last 15 minutes (BigDecimal) 
	 * @exception StockException on input errors.
	 * @see StockBean, TradeBean, StockException
	 */
	public abstract BigDecimal getStockPriceInRange(Calendar time, List<TradeBean> list, StockBean stock) throws StockException;

}
