package com.supersimplestock.service;

import java.math.BigDecimal;
import java.util.List;


import com.supersimplestock.exception.StockException;
import com.supersimplestock.model.*;
/**
 * <h1>IStock</h1>
 * 
 * The IStock interface defines the methods for the business logic for Stocks.
 *
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */

public interface IStock {
	/**
	 * This method is used to calculate the Dividend Yield for the given Stock.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @return The Dividend Yield value, in BigDecimal format.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */
	public abstract BigDecimal getDividendYield (StockBean stock)throws StockException;
	/**
	 * This method is used to calculate the P/E Ratio for the given Stock.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @return The P/E Ratio value, in BigDecimal format.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */
	public abstract BigDecimal getPERatio(StockBean stock)throws StockException;
	/**
	 * This method is used to update the TickerPrice for the given Stock and list of trades.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @param list a TradeBean List. 
	 * @return  the TickerPrice value updated.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException, TradeBean
	 */
	public abstract BigDecimal updateStockPrice(StockBean stock, List<TradeBean> list)throws StockException;

	
}
