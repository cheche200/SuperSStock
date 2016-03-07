package com.supersimplestock.data;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.supersimplestock.model.*;

/**
 * <h1>StockDAO (Data Access Object)</h1>
 * 
 * The DAO's class simulates the access to a data source that can be replace in
 * the future.
 * <p>
 * <b>NOTE:</b> This simulation assumes that there is not duplicated Stocks with
 * the same Symbols.
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */

@Component
public class StockDAO {
	
	/**
	 * This function is used to return a loaded list of Stocks in to the System.
	 * Each Stock is an object, and his values must be given manually.
	 * 
	 * @return List <StockBean> a list with StockBean that are going to be used
	 *         on the system.
	 * @see StockBean
	 */
	
	@Autowired
	StockBean stockA;
	@Autowired
	StockBean stockB;
	@Autowired
	StockBean stockC;
	@Autowired
	StockBean stockD;
	@Autowired
	StockBean stockE;
	
	
	public List<StockBean> loadStocks() {
		List<StockBean> list = new ArrayList<StockBean>();
		
		stockA.setSymbol("TEA");
		stockA.setStockType(StockType.COMMON);
		stockA.setLastDividend(0);
		stockA.setParValue(new BigDecimal(100.0));
		stockA.setTickerPrice(new BigDecimal(100.0)); 
		list.add(stockA);

		stockB.setSymbol("POP");
		stockB.setStockType(StockType.COMMON);
		stockB.setLastDividend(8);
		stockB.setParValue(new BigDecimal(100.0));
		stockB.setTickerPrice(new BigDecimal(100.0));
		list.add(stockB);

		stockC.setSymbol("ALE");
		stockC.setStockType(StockType.COMMON);
		stockC.setLastDividend(23);
		stockC.setParValue(new BigDecimal(60.0)); 
		stockC.setTickerPrice(new BigDecimal(60.0));
		list.add(stockC);

		stockD.setSymbol("GIN");
		stockD.setStockType(StockType.PREFERRED);
		stockD.setLastDividend(8);
		stockD.setFixedDividend(0.02);
		stockD.setParValue(new BigDecimal(100.0));
		stockD.setTickerPrice(new BigDecimal(100.0)); 
		list.add(stockD);

		stockE.setSymbol("JOE");
		stockE.setStockType(StockType.COMMON);
		stockE.setLastDividend(13);
		stockE.setParValue(new BigDecimal(250.0));
		stockE.setTickerPrice(new BigDecimal(250.0));
		list.add(stockE);
		
		return list;

	}

}
