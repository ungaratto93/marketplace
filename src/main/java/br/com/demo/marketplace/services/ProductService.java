package br.com.demo.marketplace.services;

import java.util.List;

import br.com.demo.marketplace.exceptions.InvalidInstallmentsException;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Product;

public interface ProductService {

	public List<Installment> buy(Product product) throws InvalidInstallmentsException;
	
}
