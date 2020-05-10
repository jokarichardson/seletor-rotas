package com.richardson.seletorrotas.support;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.model.ResponseMessage;

@RunWith(SpringRunner.class)
public class ResponseUtilsTest {

	@InjectMocks
	private ResponseUtils responseUtils;

	@Test
	public void shouldCreateAResponseMessage() {
		ResponseMessage responseMessage = ResponseUtils.criaResponseMessage(200, "Erro", "Mensagem", "classeExcecao",
				"/caminho");

		assertNotNull(responseMessage);
	}

}
