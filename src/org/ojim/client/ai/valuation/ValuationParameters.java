package org.ojim.client.ai.valuation;

public class ValuationParameters {

	public final static double streetPrice = 1;
	public final static int baseCash = 200;
	public final static double averageCashPercentage = 0.01;
	public final static double maxCashPercentage = 0.05;
	public final static int[] StreetValue = { 1, 1, 1 };
	public final static double mortgageFactor = 0.5;

	public final static int getStreetValue(int id) {
		return 1;
	}

}
