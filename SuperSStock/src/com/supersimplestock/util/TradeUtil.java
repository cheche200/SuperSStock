package com.supersimplestock.util;


import java.util.Random;

import com.supersimplestock.model.TradeType;
/**
 * <h1>TradeUtil</h1>
 * 
 * The TradeUtil class implements utility methods to simulate the Stock Trades. 
 *
 * 
 * @author Jose Calderon joseenrique86@gmail.com
 * @version 1.0
 * @since 2016-02-13
 *
 */
public class TradeUtil {
	/**
	 * This method is used to get a random integer.
	 * 
	 * @param min minimum value expected.
	 * @param max maximum value to expected.
	 * @return integer random value.
	 */
	public static int randIntInRange(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	/**
	 * This method is used to get a random TradeType
	 * 
	 * 
	 * @return random TradeType.
	 * @see TradeType
	 */
	public static TradeType randTradeType() {
		final TradeType[] TradeTypes = { TradeType.BUY, TradeType.SELL };
		final int enumSize = TradeTypes.length;
		final Random random = new Random();
		return TradeTypes[random.nextInt(enumSize)];
	}


}