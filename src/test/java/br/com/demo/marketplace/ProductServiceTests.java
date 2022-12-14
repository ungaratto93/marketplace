package br.com.demo.marketplace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.demo.marketplace.exceptions.InvalidInstallmentsException;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;
import br.com.demo.marketplace.models.SelicFee;
import br.com.demo.marketplace.services.ProductService;
import br.com.demo.marketplace.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

	@InjectMocks // objeto meio burro
	private ProductServiceImpl productService;

	@Mock // objeto burro
	private SelicFee selicFee;

	@BeforeEach
	public void setup() {
		ProductService productService = new ProductServiceImpl();
	}
	
	@DisplayName("Junit Teste para metodo de comprar produto com juros")
	@Test
	public void givenProductObject_whenBuy_thenReturnListProductObject_withFees() throws InvalidInstallmentsException {
		Payment payment = new Payment(100.00, 24);
		Product product = new Product("001","teste", 500.00, payment);

		// quando chamar esse
		List<Installment> payments = productService.buy(product);
		
		// entao valido a saida
		assertThat(payments).isNotNull();
		assertThat(payments.get(0).getFee()).isEqualTo(13.65);
		assertThat(payments.get(0).getValue()).isEqualTo(244.16666666666669);
		assertThat(payments.size()).isEqualTo(24);
	}
	
	@DisplayName("Junit Teste para metodo de comprar produto sem juros")
	@Test
	public void givencurrentIntallmentProductObject_whenBuy_thenReturnListProductObject_withoutFees() throws InvalidInstallmentsException {
		Payment payment = new Payment(100.00, 6);
		Product product = new Product("001","teste", 500.00, payment);

		// quando chamar esse
		List<Installment> payments = productService.buy(product);

		// entao valido a saida
		assertThat(payments).isNotNull();
		assertThat(payments.get(0).getFee()).isEqualTo(0.00);
		assertThat(payments.get(0).getValue()).isEqualTo(66.66666666666667);
		assertThat(payments.size()).isEqualTo(6);

	}
	
	
}
