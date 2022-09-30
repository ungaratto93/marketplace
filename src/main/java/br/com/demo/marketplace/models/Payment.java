package br.com.demo.marketplace.models;

public class Payment {

	private Double downPayment;
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
