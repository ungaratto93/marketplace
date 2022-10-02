package br.com.demo.marketplace.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payment {

	@JsonProperty("downPayment")
	private Double downPayment;

	@JsonProperty("installments")
	private int installments;
	
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
	
	public Double getPaymentInstallment(Double debtValue) {
		return (debtValue / installments);
	}

	public Double getDebtValue(Double price) {
		return (price - getDownPayment());		
	}
}
