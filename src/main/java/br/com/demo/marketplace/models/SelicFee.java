package br.com.demo.marketplace.models;

public class SelicFee {

	private final Double value = 13.65;
	private final int installmentsWithOutSelic = 6;
	
	public Double getValue() {
		return value;
	}
	
	public int getNumberOfInstallmentsWithOut() {
		return installmentsWithOutSelic;
	}
	
	public  Double canApplyIn(int installments) {
		return (installments > this.getNumberOfInstallmentsWithOut()) ? this.getValue() : 0.00;
	}


}
