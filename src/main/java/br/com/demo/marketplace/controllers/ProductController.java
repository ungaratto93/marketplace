package br.com.demo.marketplace.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.demo.marketplace.mappers.jsonMapper;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;
import br.com.demo.marketplace.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productServiceImpl;
	
	@PostMapping("/products/buy")
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Payment>> buy(@RequestBody String request) throws JsonParseException, JsonMappingException, IOException {
		productServiceImpl.buy(jsonMapper.mapFromJson(request, Product.class));
		return new ResponseEntity(null, HttpStatus.OK);
	}
	
}
