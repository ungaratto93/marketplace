package br.com.demo.marketplace;

import br.com.demo.marketplace.mappers.jsonMapper;
import br.com.demo.marketplace.models.Installment;
import br.com.demo.marketplace.models.Payment;
import br.com.demo.marketplace.models.Product;
import br.com.demo.marketplace.services.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTests {

	@Mock
	ProductServiceImpl productServiceImpl;

	@Autowired
	MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void shouldBuyProduct() throws Exception {
		String uri = "/products/buy";
		Payment payment = new Payment(100.00, 24);
		Product product = new Product("teste", "1", 500.00, payment);

		List<Installment> payments = new ArrayList<>();
		for (int index = 1; index <= 24; index++) {
			payments.add(new Installment(
					index, 244.16666666666669,
					13.65));
		}

		when(productServiceImpl.buy(any(Product.class))).thenReturn(payments);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(jsonMapper.mapToJson(product)))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, jsonMapper.mapToJson(payments));
	}

}
