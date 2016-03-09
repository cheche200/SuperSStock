package com.supersimplestock.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.supersimplestock.exception.*;
import com.supersimplestock.model.StockBean;
import com.supersimplestock.model.TradeBean;

/**
 * <h1>Stock</h1>
 * 
 * The Stock class implements the business logic for Stocks.
 *
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */
@Component
public class Stock implements IStock {
	private static Logger log = Logger.getLogger(Stock.class);
	private static final MathContext PRECISION = MathContext.DECIMAL128;
	private static final String source = Stock.class.getName();

	/**
	 * This method is used to calculate the Dividend Yield for the given Stock.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @return The Dividend Yield value, in BigDecimal format.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */
	
	
	public BigDecimal getDividendYield(StockBean stock) throws StockException {
		BigDecimal result = new BigDecimal(0);
		BigDecimal dividendYeld = new BigDecimal(0);
		if (stock == null) {
			new StockException(
					source + " can not calculate Dividend Yield, the StockBean is null");
		} else if (stock.getTickerPrice().compareTo(BigDecimal.ZERO) == 0) {
			new StockException(source + " can not calculate Dividend Yield for "
					+ stock.getSymbol() + ", the Ticker Price can not be 0.");
		} else if (stock.getStockType() == null) {
			new StockException(source + " can not calculate Dividend Yield for "
					+ stock.getSymbol() + ",  the Stock type is Null.");
		} else {
			log.debug("Calculating the Dividend Yield for ["
					+ stock.getSymbol() + "]");
			switch (stock.getStockType()) {
			case COMMON:
				dividendYeld = new BigDecimal(stock.getLastDividend()).divide(
						stock.getTickerPrice(), PRECISION);
				log.debug("Dividend Yield for COMMON [" + stock.getSymbol()
						+ "] is "
						+ dividendYeld.stripTrailingZeros().toPlainString());
				break;
			case PREFERRED:
				BigDecimal fixedDividend = new BigDecimal(
						stock.getFixedDividend());
				dividendYeld = fixedDividend.multiply(stock.getParValue())
						.divide(stock.getTickerPrice(), PRECISION);
				log.debug("Dividend Yield for PREFERRED [" + stock.getSymbol()
						+ "] is "
						+ dividendYeld.stripTrailingZeros().toPlainString());
				break;
			default:
				new StockException(source
						+ " can not calculate Dividend Yield for "
						+ stock.getStockType()
						+ ", the Stock type is not supported.");
			}
			result = dividendYeld;
		}

		return result.stripTrailingZeros();
	}
	/**
	 * This method is used to calculate the P/E Ratio for the given Stock.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @return The P/E Ratio value, in BigDecimal format.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */

	public BigDecimal getPERatio(StockBean stock) throws StockException {
		BigDecimal result = new BigDecimal(0);
		if (stock == null) {
			new StockException(source
					+ " can not calculate PE Ratio, the StockBean is null.");
		} else if (stock.getLastDividend() == 0) {
			new StockException(source + " can not calculate PE Ratio for "
					+ stock.getSymbol() + ", last dividend can not be 0.");
		} else {
			log.debug("Calculating the P/E Ratio for [" + stock.getSymbol()
					+ "]");
			BigDecimal peRatio = stock.getTickerPrice().divide(
					new BigDecimal(stock.getLastDividend()), PRECISION);
			result = peRatio.stripTrailingZeros();
			log.debug("P/E Ratio for [" + stock.getSymbol() + "] is "
					+ peRatio.stripTrailingZeros().toPlainString());
		}
		return result.stripTrailingZeros();

	}
	/**
	 * This method is used to update the TickerPrice for the given Stock and list of trades.
	 * 
	 * @param stock the StockBean on which the calculation is performed.
	 * @param list a TradeBean List. 
	 * @return TickerPrice value updated.
	 * @exception StockException on input errors.
	 * @see StockBean, StockException, TradeBean
	 */
	
	public BigDecimal updateStockPrice(StockBean stock, List<TradeBean> list)
			throws StockException {
		BigDecimal result = new BigDecimal(0);
		int totalQty = 0;
		if (stock == null || list.isEmpty()) {
			new StockException(
					source + " can not Update Stock Price, the StockBean is null or TradeBean list is empty.");
		} else {
		    log.debug("Calculating Stock Price for: ["+stock.getSymbol()+"]");
			for (TradeBean trade : list) {
				
				if (trade.getStock().getSymbol().equals(stock.getSymbol())) {
					result = result.add(trade.getStockPrice().multiply(
							new BigDecimal(trade.getShareQnty())));
					log.debug("Trade found for: ["+stock.getSymbol()+"] price:"+trade.getStockPrice()
							+" shares: "+trade.getShareQnty());
					totalQty += trade.getShareQnty();
					log.debug("Numbers of Shares on the List is: "+totalQty);
				}
			}
			if (totalQty == 0) {
				new StockException(source + " there is no Trade for "
						+ stock.getSymbol());

			} else {
				if (result.compareTo(BigDecimal.ZERO) == -1) {
					new StockException(source + " the price of "
							+ stock.getSymbol() + " is 0.");
				} else {
					result = result.divide(new BigDecimal(totalQty), PRECISION);
					log.debug("the Stock Price for "
							+ stock.getSymbol() + " has been updated to: "
							+ result.stripTrailingZeros().toPlainString());
					stock.setTickerPrice(result);
				}
			}
		}
		return result.stripTrailingZeros();

	}

}
