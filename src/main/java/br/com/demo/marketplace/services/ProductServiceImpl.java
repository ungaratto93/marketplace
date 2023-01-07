package br.com.demo.marketplace.services;

import java.util.ArrayList;
import java.util.List;

import br.com.demo.marketplace.exceptions.InvalidInstallmentsException;
import br.com.demo.marketplace.models.Debt;
import br.com.demo.marketplace.models.SelicFee;
import br.com.demo.marketplace.services.builders.DebtBuilder;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Installment> buy(Product product) throws InvalidInstallmentsException {
		Payment pay = product.getPayment();

		int installments = pay.getInstallments();
		if (installments <= 0)
			throw new InvalidInstallmentsException("Invalid Installments");

		Debt debt = new DebtBuilder()
				.withDebtValue(pay.calculateDebts(product.getPrice()))
				.withPayInstallment(pay.calculateAmountPayPerInstallmentBy())
				.withFee(new SelicFee().canApplyIn(installments))
				.build();

		List<Installment> installmentsToPayDebt = new ArrayList<>(installments);
		for (int installmentNumber = 1; installmentNumber <= installmentNumber; installmentNumber++) {
			installmentsToPayDebt.add(new Installment(installmentNumber, debt.getInstallment(), debt.getFee()));
		}
		return installmentsToPayDebt;
	}

}
