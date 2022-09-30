package br.com.demo.marketplace.models;

public class SelicFee {

	private Double value = 13.65;
	private int installmentsWithOutSelic = 6;
	
	public Double getValue() {
		return value;
	}
	
	public int getNumberOfInstallmentsWithOut() {
		return installmentsWithOutSelic;
	}
	
	public  Double applyIn(int installments) {
		return (installments > this.getNumberOfInstallmentsWithOut()) ? this.getValue() : 0.00;
	}


}
