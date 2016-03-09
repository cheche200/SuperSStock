package com.supersimplestock.data;

import java.math.BigDecimal;
import java.util.Calendar;
import org.apache.log4j.*;

import com.supersimplestock.model.*;
import com.supersimplestock.util.*;

/**
 * <h1>TradeDAO (Data Access Object)</h1>
 * 
 * The DAO's class simulates the access to a data source that can be replace in
 * the future.
 * <p>
 * <b>NOTE:</b> This simulation uses random Data provided by the TradeUtil
 * class.
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */
public class TradeDAO {
	
	private static Logger log = Logger.getLogger(TradeDAO.class);
	/**
	 * This function is used to return a loaded list of TradeBean in to the
	 * System. Each trade is created with random data.
	 * 
	 * @param stock
	 *            this is the StockBean on which are going to be generated the
	 *            trades.
	 * @return List <TradeBean> a list with the TradeBean that are going to be
	 *         used on the system.
	 * @see StockBean, TradeBean, TradeUtil
	 * 
	 */
	public static TradeBean createRandomTrade(StockBean stock) {
		Calendar now = Calendar.getInstance();
	
			TradeBean trade = new TradeBean();
			trade.setStock(stock);
			now.add(Calendar.MINUTE, TradeUtil.randIntInRange(-2, -1));
			trade.setTradeTime(now.getTime());
			trade.setShareQnty(TradeUtil.randIntInRange(2, 100));
			trade.setType(TradeUtil.randTradeType());
			BigDecimal price=new BigDecimal(TradeUtil.randIntInRange(-2, 2));
			trade.setStockPrice(stock.getTickerPrice().add(price));
			log.debug(trade.toString()+" created");
		
		return trade;

	}

}
