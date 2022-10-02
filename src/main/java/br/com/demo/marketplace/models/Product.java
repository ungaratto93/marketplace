package br.com.demo.marketplace.models;

public class Product {

	private String name;	
	private String code;
	private Double price;
	private Payment payment;
	
	public Product(String name, String code, Double price, Payment payment) {
		this.name = name;
		this.code = code;
		this.price = price;
		this.payment = payment;
	}

	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}
	
	public Double getPrice() {
		return this.price;
	}

	public Payment getPayment() {
		return this.payment;
	}
	
}
