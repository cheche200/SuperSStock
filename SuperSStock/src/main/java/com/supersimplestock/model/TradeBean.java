package com.supersimplestock.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * <h1>TradeBean</h1>
 * 
 * The TradeBean class is used to store Trades information.
 * 
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 * 
 *
 */
@Component
public class TradeBean {
	
	private StockBean stock;
	private Date tradeTime;
	private int shareQnty;
	private TradeType tradeType;
	private BigDecimal stockPrice;
	/**
	 * 
	 * @return the StockBean on which the trade is performed
	 * @see StockBean
	 */
	public StockBean getStock() {
		return stock;
	}
	/**
	 * 
	 * @param stock the StockBean on which the trade is performed
	 * @see StockBean
	 */
	public void setStock(StockBean stock) {
		this.stock = stock;
	}
	/**
	 * 
	 * @return the time in Date format of the Trade.
	 */
	public Date getTradeTime() {
		return tradeTime;
	}
	/**
	 * 
	 * @param tradeTime the time in Date format of the Trade.
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	/**
	 * 
	 * @return the number of shares bought or sold on the trade.
	 */
	public int getShareQnty() {
		return shareQnty;
	}
	/**
	 * 
	 * @param shareQnty the number of shares bought or sold on the trade.
	 */
	public void setShareQnty(int shareQnty) {
		this.shareQnty = shareQnty;
	}
	/**
	 * 
	 * @return the Buy or Sell indicator.
	 * @see TradeType
	 */
	public TradeType getTradeType() {
		return tradeType;
	}
	/**
	 * 
	 * @param type the Buy or Sell indicator.
	 * @see TradeType
	 */
	public void setType(TradeType type) {
		this.tradeType = type;
	}
	/**
	 * 
	 * @return the price at which the operation is performed (in pennies).
	 */
	public BigDecimal getStockPrice() {
		return stockPrice;
	}
	/**
	 * 
	 * @param stockPrice the price at which the operation is performed (in pennies).
	 */
	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}
@Override
	public String toString() {
		return "["+this.getTradeType()+"] "+" ["+this.getShareQnty()+"] "+
				" ["+this.getStock().getSymbol()+"] "+" Stocks "+" at "+
				 this.getStockPrice()+" on "+this.getTradeTime();
	}
	
}
