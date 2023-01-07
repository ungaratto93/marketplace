package br.com.demo.marketplace.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.demo.marketplace.dtos.requests.PostProductRequestDTO;
import br.com.demo.marketplace.exceptions.InvalidInstallmentsException;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.services.ProductService;
import br.com.demo.marketplace.services.ProductServiceImpl;
import io.swagger.annotations.Api;

@RestController
public class ProductController {

	private ProductService productServiceImpl;

	public ProductController() {
		this.productServiceImpl = new ProductServiceImpl();
	}

	@PostMapping(path = "/products/buy", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Installment>> buy(@RequestBody PostProductRequestDTO postProductRequestDTO) {
		List<Installment> installments;
		ResponseEntity<List<Installment>> response;
		try {
			installments = productServiceImpl
					.buy(postProductRequestDTO.toModel(withPayment(postProductRequestDTO)));
			response = new ResponseEntity<List<Installment>>(installments, HttpStatus.OK);
		} catch (InvalidInstallmentsException e) {
			response = new ResponseEntity<List<Installment>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	private Payment withPayment(PostProductRequestDTO request) {
		return new Payment(request.getPayment().getDownPayment(), request.getPayment().getInstallments());
	}

}
