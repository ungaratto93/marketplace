package br.com.demo.marketplace.helpers;

public class HelperFees {

	private static Double selic = 13.65;
	
	public Double getSelic() {
		return selic;
	}
	
	public Double getApplySelic(int installments) {
		return (installments > 6) ? this.getSelic() : 0.00;
	}
}
