package br.com.demo.marketplace.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

	@JsonProperty("downPayment")
	private Double downPayment;

	@JsonProperty("installments")
	private int installments;

	private Double debtPrice;
	
	public Payment(Double downPayment, int installments) {
		this.downPayment = downPayment;
		this.installments = installments;
	}

	public Double getDownPayment() {
		return this.downPayment;
	}

	public int getInstallments() {
		return this.installments;
	}
	
	private Double getDebtPrice() {
		return this.debtPrice;
	}

	private void setDebtPrice(Double debtPrice) {
		this.debtPrice = debtPrice;
	}

	public Double calculateAmountPayPerInstallmentBy() {
		return (getDebtPrice() / getInstallments());
	}

	public Double calculateDebts(Double debtPrice) {
		setDebtPrice(debtPrice);
		return (getDebtPrice() - getDownPayment());		
	}


}
