package com.richardson.seletorrotas.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richardson.seletorrotas.config.RotasBeanTestConfiguration;
import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.mocks.RotaMock;
import com.richardson.seletorrotas.model.dto.Rota;
import com.richardson.seletorrotas.support.MessageUtils;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RotasBeanTestConfiguration.class)
public class SeletorRotasTest {

	private SeletorRotas seletorRotas;

	@Autowired
	private List<Rota> rotas;

	@Mock
	private MessageUtils messageUtils;

	@Before
	public void setUp() {
		this.seletorRotas = new SeletorRotas(rotas, this.messageUtils);
	}

	@Test
	public void deveRetornarRotasCadastradas() {
		List<Rota> actualRotaList = this.seletorRotas.recuperarRotas();

		assertNotNull(actualRotaList);
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoRetornarRotasCadastradas() {
		this.seletorRotas = new SeletorRotas(null, this.messageUtils);
		this.seletorRotas.recuperarRotas();
	}

	@Test
	public void deveRegistrarRota() {
		Rota rota = RotaMock.criarRota();
		int totalRotas = this.rotas.size() + 1;

		this.seletorRotas.registrarRota(rota);

		assertEquals(totalRotas, this.rotas.size());
	}

	@Test
	public void deveRecuperarAMelhorRota() {
		String expectedMelhorRota = "BRC - SCL - ORL - CDG";

		String actualMelhorRota = this.seletorRotas.recuperarMelhorRota("BRC", "CDG");

		assertNotNull(actualMelhorRota);
		assertTrue(expectedMelhorRota.equalsIgnoreCase(actualMelhorRota));
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoRecuperarAMelhorRotaPorNaoHaveremRotasCadastradas() {
		this.seletorRotas = new SeletorRotas(null, this.messageUtils);
		this.seletorRotas.recuperarMelhorRota("BRC", "CDG");
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoRecuperarAMelhorRotaPorNaoHaverAOrigemInformada() {
		this.seletorRotas.recuperarMelhorRota("CDG", "GRU");
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoRecuperarAMelhorRotaPorNaoEncontrarRota() {
		this.seletorRotas.recuperarMelhorRota("BRC", "GRU");
	}
}
