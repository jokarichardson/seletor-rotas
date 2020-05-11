package com.richardson.seletorrotas.application;

import java.security.CodeSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.SeletorRotasStarterApplicationTest;
import com.richardson.seletorrotas.logic.SeletorRotas;

@RunWith(SpringRunner.class)
public class WebApplicationTest {

	@InjectMocks
	private WebApplication webApplication;

	@Mock
	private SeletorRotas seletorRotas;

	@Test
	public void deveExecutarWebApplication() throws Exception {
		CodeSource codeSource = SeletorRotasStarterApplicationTest.class.getProtectionDomain().getCodeSource();
		this.webApplication
				.run(new String[] { codeSource.getLocation().toURI().getPath().concat("/csv/input-routes.csv") });
	}
}
