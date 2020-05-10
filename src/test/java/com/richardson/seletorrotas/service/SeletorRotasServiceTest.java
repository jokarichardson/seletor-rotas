package com.richardson.seletorrotas.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.mocks.RotaMock;
import com.richardson.seletorrotas.model.dto.Rota;

@RunWith(SpringRunner.class)
public class SeletorRotasServiceTest {
	@InjectMocks
	private SeletorRotasService seletorRotasService;

	@Mock
	private List<Rota> rotas;

	@Test
	public void deveRetornarListaDeRotas() {
		List<Rota> actualRotas = this.seletorRotasService.recuperarRotas();

		assertNotNull(actualRotas);
	}

	@Test
	public void deveRetornarARotaMaisBarata() {
		String actualRota = this.seletorRotasService.recuperarMelhorRota("ORI", "DES");

		assertNotNull(actualRota);
	}

	@Test
	public void deveRegistrarRota() {
		Rota rota = RotaMock.criarRota();

		this.seletorRotasService.registrarRota(rota);

		verify(this.rotas, times(1)).add(any());
	}

}
