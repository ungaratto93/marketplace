package br.com.demo.marketplace.services.builders;

import br.com.demo.marketplace.models.Debt;

public class DebtBuilder {

    private Debt debt;

    public DebtBuilder() {
        this.debt = new Debt(null, null, null);
    }

    public DebtBuilder withDebtValue(Double debtValue) {
        this.debt.setValue(debtValue);
        return this;
    }
    
    public DebtBuilder withPayInstallment(Double paymentInstallment2) {
        this.debt.setPayInstallment(paymentInstallment2);
        return this;
    }

    public DebtBuilder withFee(Double selic) {
        this.debt.setFee(selic);
        return this;
    }

    public Debt build() {
        return this.debt;
    }
}

