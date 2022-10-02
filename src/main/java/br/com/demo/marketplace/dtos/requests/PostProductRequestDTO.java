package br.com.demo.marketplace.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;

public class PostProductRequestDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("code")
	private String code;

	@JsonProperty("price")
	private Double price;

	@JsonProperty("payment")
	private Payment payment;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public Double getPrice() {
		return price;
	}

	public Payment getPayment() {
		return payment;
	}

	public Product toModel(Payment payment) {
		return new Product(name, code, price, payment);
	}

}
