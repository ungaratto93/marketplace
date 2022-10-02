package br.com.demo.marketplace.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.demo.marketplace.dtos.requests.PostProductRequestDTO;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.services.ProductService;
import br.com.demo.marketplace.services.ProductServiceImpl;

@RestController
public class ProductController {

	private ProductService productServiceImpl;

	public ProductController() {
		this.productServiceImpl = new ProductServiceImpl();
	}

	@PostMapping(path = "/products/buy", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Installment>> buy(@RequestBody PostProductRequestDTO postProductRequestDTO)
			throws JsonParseException, JsonMappingException, IOException {
		List<Installment> installments = productServiceImpl.buy(postProductRequestDTO.toModel(withPayment(postProductRequestDTO)));
		return new ResponseEntity<List<Installment>>(installments, HttpStatus.OK);
	}

	private Payment withPayment(PostProductRequestDTO request) {
		return new Payment(request.getPayment().getDownPayment(), request.getPayment().getInstallments());
	}

}
