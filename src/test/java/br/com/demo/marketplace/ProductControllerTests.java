package br.com.demo.marketplace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.demo.marketplace.mappers.jsonMapper;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;
import br.com.demo.marketplace.services.ProductService;
import br.com.demo.marketplace.services.ProductServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class ProductControllerTests {

	@Mock
	private ProductService productService;

	@Autowired
	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		productService = new ProductServiceImpl();
	}

	@Test
	public void shouldBuyProduct() throws Exception {
		String uri = "/products/buy";
		Payment payment = new Payment(100.00, 24);
		Product product = new Product("teste", "1", 500.00, payment);

		List<Installment> payments = new ArrayList<>();
		for (int index = 0; index < 24; index++) {
			payments.add(new Installment(index, 66.66, 1.15));
		}

		when(productService.buy(any())).thenReturn(payments);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonMapper.mapToJson(product))).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, jsonMapper.mapToJson(payments));
	}

}
