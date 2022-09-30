package br.com.demo.marketplace.models;

public class Installment {

	private int installmentNumber;
	private Double value;
	private Double fee;
	
	public Installment(int installmentNumber, Double value, Double fee) {
		this.installmentNumber = installmentNumber;
		this.value = value;
		this.fee = fee;
	}
	public int getInstallmentNumber() {
		return this.installmentNumber;
	};
	
	public Double getValue() {
		return this.value;
	}
	
	public Double getFee() {
		return this.fee;
	}
	
	@Override
	public String toString() {
		return "{ numeroParcela:" + this.getInstallmentNumber() + ", valor:" + this.getValue() +", taxaJurosAoMes:" + this.getFee() + "}";
	}



}
