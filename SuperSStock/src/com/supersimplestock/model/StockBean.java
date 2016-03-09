package com.supersimplestock.model;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * <h1>StockBean</h1>
 * 
 * The StockBean class is used to store Stock information.
 * 
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 * 
 *
 */

@Component
@Scope("prototype")
public class StockBean {
	
	private String symbol;
	private StockType stockType;
	private int lastDividend;
	private double fixedDividend;
	private BigDecimal parValue;
	/**
	 * tickerPrice is used to store the price of the Stock based on the Trades made.
	 */
	private BigDecimal tickerPrice;
	/**
	 * @return String with the Symbol of the Stock.
	 * 
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * 
	 * @param symbol The symbol of the Stock (must be unique).
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * 
	 * @return the type of the Stock given by the Enum StockType.
	 * @see StockType 
	 */
	public StockType getStockType() {
		return stockType;
	}
	/**
	 * 
	 * @param stockType a Enum StockType option.
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}
	/**
	 * 
	 * @return the Stock last Dividend as an integer.
	 */
	public int getLastDividend() {
		return lastDividend;
	}
	/**
	 * 
	 * @param lastDividend the dividend of the Stock as an integer.
	 */
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}
	/**
	 * 
	 * @return the fixed Dividend for a Preffered Stock (Percentage Example: 2 for 2%) 
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}
	/**
	 * 
	 * @param fixedDividend the fixed Dividend for a Preffered Stock (Percentage Example: 2 for 2%)
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	/**
	 * 
	 * @return the Par value of the Stock (in pennies).
	 */
	public BigDecimal getParValue() {
		return parValue;
	}
	/**
	 * 
	 * @param parValue the Par value of the Stock (in pennies).
	 */
	public void setParValue(BigDecimal parValue) {
		this.parValue = parValue;
	}
	/**
	 * 
	 * @return the Ticker Price of the Stock (in pennies).
	 */
	public BigDecimal getTickerPrice() {
		return tickerPrice;
	}
	/**
	 * 
	 * @param tickerPrice the Ticker Price of the Stock (in pennies).
	 */
	public void setTickerPrice(BigDecimal tickerPrice) {
		this.tickerPrice = tickerPrice;
	}
	@Override
	public String toString() {
		return "Stock: "+this.getSymbol()+" type: "+this.getStockType()
				+" Par Value: "+this.getParValue().toPlainString()
				+" Last Dividend: "+this.getLastDividend()
				+" Fixed Dividend: "+this.getFixedDividend()
				+" Ticker Price: "+this.getTickerPrice();
	}
	
	
	
	

}
