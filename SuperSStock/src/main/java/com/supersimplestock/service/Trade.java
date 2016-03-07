package com.supersimplestock.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.supersimplestock.exception.StockException;
import com.supersimplestock.model.StockBean;
import com.supersimplestock.model.TradeBean;

/**
 * <h1>Trade</h1>
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
public class Trade implements ITrade {
	private static Logger log = Logger.getLogger(Trade.class);
	private static final MathContext PRECISION = MathContext.DECIMAL128;
	private static final String source = Trade.class.getName();

	/**
	 * This method is used to calculate the GBCE All Share Index using the
	 * geometric mean of prices for all stocks.
	 * 
	 * @param list StockBean list.
	 * @return the GBCE All Share Index, in BigDecimal
	 * @exception StockException on input errors.
	 * @see StockBean, StockException
	 */
	
	public BigDecimal getAllShareIndex(List<StockBean> list)
			throws StockException {
		BigDecimal shareIndex = new BigDecimal(1);
		if (list.isEmpty()) {
			new StockException(source
					+ " there is no elements to calculate the Share Index");
		} else {
			
			for (StockBean stock : list) {
				log.debug("Calculating All Share Index. Reading ["+stock.getSymbol()+"]");
				if (stock.getTickerPrice().compareTo(new BigDecimal(0)) == 0) {
					new StockException(
							source + " the Ticker Price of "
									+ stock.getSymbol()
									+ " is 0. It will not be taken in count for the Share Index Calculation.");
				} else {
					shareIndex = shareIndex.multiply(stock.getTickerPrice());
					log.debug("The Ticker Price of ["+stock.getSymbol()+"] is "
					+stock.getTickerPrice().stripTrailingZeros().toPlainString());
				}
			}
			shareIndex = new BigDecimal(Math.pow(shareIndex.doubleValue(),
					1.0 / list.size()), PRECISION);
			log.debug("The Share Index is: "+shareIndex.stripTrailingZeros().toPlainString());
		}
		return shareIndex.stripTrailingZeros();

	}

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
	
	public BigDecimal getStockPriceInRange(Calendar time, List<TradeBean> list,
			StockBean stock) throws StockException {
		BigDecimal result = new BigDecimal(0);
		int totalQty = 0;
		Calendar now = Calendar.getInstance();
		if (list.isEmpty()) {
			new StockException(source + " there is no Trade for "
					+ stock.getSymbol() + ".");
			result = stock.getParValue();
		} else {
		    log.debug("Calculating Stock Price for: ["+stock.getSymbol()+"]");
			for (TradeBean trade : list) {
				
				if (trade.getStock().getSymbol().equals(stock.getSymbol())) {
					if (trade.getTradeTime().compareTo(now.getTime()) < 0
							&& trade.getTradeTime().compareTo(time.getTime()) > 0) {
						result = result.add(trade.getStockPrice().multiply(
								new BigDecimal(trade.getShareQnty())));
						log.debug("Trade found for: ["+stock.getSymbol()+"] price:"+trade.getStockPrice()
								+" shares: "+trade.getShareQnty());
						totalQty += trade.getShareQnty();
						log.debug("Numbers of Shares on the List is: "+totalQty);

					}
				}
			}
			if (totalQty == 0) {
				new StockException(source + " there is no Trade for "
						+ stock.getSymbol() + " in that period of time");
				result = stock.getParValue().stripTrailingZeros();
			} else {
				if (result.compareTo(BigDecimal.ZERO) == -1) {
					new StockException(source + " the price of "
							+ stock.getSymbol() + " is 0.");
				} else {
					result = result.divide(new BigDecimal(totalQty), PRECISION);
					log.debug("the Stock Price for " + stock.getSymbol()
							+ " in the time range given is "
							+ result.stripTrailingZeros().toPlainString());
				}
			}
		}
		return result.stripTrailingZeros();
	}

}
