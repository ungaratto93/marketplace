package br.com.demo.marketplace.services;

import java.util.ArrayList;
import java.util.List;

import br.com.demo.marketplace.models.Debt;
import br.com.demo.marketplace.models.SelicFee;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Product;

public class ProductServiceImpl implements ProductService {
	
	@Override
	public List<Installment> buy(Product product) {
		int installments = product.getPayment().getInstallments();
		if (installments <= 0) {
			throw new IllegalArgumentException("Invalid Installments");
		}

		Double selic = new SelicFee().applyIn(installments);
		Double debtValue = product.getPayment().getDebtValue(product.getPrice());
		Double paymentInstallment = product.getPayment().getPaymentInstallment(debtValue);
		Debt debt = new Debt(debtValue, paymentInstallment, selic);

		List<Installment> listOfInstallments = new ArrayList<>();
		for (int installment = 1; installment <= installments; installment++) {
			listOfInstallments.add(new Installment(installment, debt.getInstallment(), debt.getFee()));
		}

		return listOfInstallments;
	}

}
