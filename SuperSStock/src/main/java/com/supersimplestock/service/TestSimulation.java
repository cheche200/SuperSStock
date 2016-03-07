package com.supersimplestock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supersimplestock.data.*;
import com.supersimplestock.exception.StockException;
import com.supersimplestock.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SuperSimpleContext.class)
/**
 * <h1>TestSimulation</h1>
 * 
 * This is the main class for the simulation.
 * 
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */
public class TestSimulation {

	private static List<StockBean> stockList;
	private static List<TradeBean> totalTradeList;
	private static Logger log = Logger.getLogger(TestSimulation.class);
	/*numTrades is the number of random trades for each Stock */
	private static final int numTrades = 15;
	@Autowired
	IStock stockService;
	@Autowired
	ITrade tradeService;
	@Autowired
	StockDAO stockDAO;
    /**
     * This is the main method for the Simulation.
     * @throws StockException
     */
	@Test
	public void simulation () throws StockException {

		totalTradeList = new ArrayList<TradeBean>();
		log.info("***Loading Stocks...");
		stockList = stockDAO.loadStocks();
		for (StockBean stock : stockList) {
			log.info(stock.toString() + " has been loaded");
		}
		log.info("***Stocks are loaded.");

		log.info("***Calculating the Dividend Yield for all the Stocks in the System");
		/* Dividend Yield Calculation */
		for (StockBean stock : stockList) {
			BigDecimal dividendYield = stockService.getDividendYield(stock);
			log.info("The Dividend Yield for: " + stock.getSymbol() + " is "
					+ dividendYield.toPlainString());
		}

		log.info("***Calculating the PE Ratio for all the Stocks in the System ");
		// PE Ratio Calculation
		for (StockBean stock : stockList) {
			BigDecimal peRatio = stockService.getPERatio(stock);
			log.info("The PE Ratio for: " + stock.getSymbol() + " is "
					+ peRatio.toPlainString());
		}

		log.info("***Creating Random Trades...");
			/* Generates random Trades for the Stocks */
		for (StockBean stock : stockList) {
			log.info("Starting Trades for: " + stock.getSymbol());
			for (int i = 0; i <= numTrades; i++) {
				TradeBean trade = TradeDAO.createRandomTrade(stock);
				log.info("Trade:"+trade.toString());
				totalTradeList.add(trade);
				stock.setTickerPrice(stockService.updateStockPrice(stock,
						totalTradeList));
				log.info("The Ticker Price for: " + stock.getSymbol()
						+ " has been updated to: "
						+ stock.getTickerPrice().toPlainString());
			}

		}

		log.info("***Stock price based on the last 15 minutes trades");
		/* Calculating the Stock Price based in the Trades made in the 
		 * last 15 minutes */
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, -15);
		for (StockBean stock : stockList) {
			BigDecimal stockPrice = tradeService.getStockPriceInRange(time,
					totalTradeList, stock);
			log.info("The Ticker Price for " + stock.getSymbol() + " is: "
					+ stockPrice);
		}

		log.info("***All Share Index");
		/* Calculating the GBCE All Share Index using the geometric mean 
		 * of prices for all stocks */
		BigDecimal shareIndex = tradeService.getAllShareIndex(stockList);
		log.info("The All Share Index Value is: " + shareIndex.toPlainString());

	}

}

