package br.com.demo.marketplace.models;

public class Debt {

	private Double value;
	private Double paymentInstallment;
	private Double fee;
	
	public Debt(Double value, Double paymentInstallment, Double fee) {
		this.value = value;
		this.paymentInstallment = paymentInstallment;
		this.fee = fee;
	}
  	
	public Double getValue() {
		return this.value;
	}
	
	public Double getPaymentInstallment() {
		return this.paymentInstallment;
	}

	public Double getFee() {
		return fee;
	}
	
	public Double getInstallment() {
		return (paymentInstallment + (paymentInstallment * fee));
	}

    public void setValue(Double debtValue) {
		this.value = debtValue;
	}

    public void setPayInstallment(Double paymentInstallment2) {
		this.paymentInstallment = paymentInstallment2;
	}

    public void setFee(Double selic) {
		this.fee = selic;
	}

}
