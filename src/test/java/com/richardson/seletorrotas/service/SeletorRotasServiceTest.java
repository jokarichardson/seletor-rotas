package com.richardson.seletorrotas.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.logic.SeletorRotas;
import com.richardson.seletorrotas.mocks.RotaMock;
import com.richardson.seletorrotas.model.Rota;

@RunWith(SpringRunner.class)
public class SeletorRotasServiceTest {
	@InjectMocks
	private SeletorRotasService seletorRotasService;

	@Mock
	private SeletorRotas seletorRotas;

	@Test
	public void deveRetornarListaDeRotas() {
		List<Rota> expectedRotasList = RotaMock.criarRotaList();
		when(this.seletorRotas.recuperarRotas()).thenReturn(expectedRotasList);

		List<Rota> actualRotas = this.seletorRotasService.recuperarRotas();

		assertNotNull(actualRotas);
		verify(this.seletorRotas, times(1)).recuperarRotas();
	}

	@Test
	public void deveRetornarARotaMaisBarata() {
		String expectedMelhorRota = "BRC - SCL - ORL - CDG";
		when(this.seletorRotas.recuperarMelhorRota(anyString(), anyString())).thenReturn(expectedMelhorRota);
		String actualMelhorRota = this.seletorRotasService.recuperarMelhorRota("BRC", "CDG");

		assertNotNull(actualMelhorRota);
		verify(this.seletorRotas, times(1)).recuperarMelhorRota(anyString(), anyString());
	}

	@Test
	public void deveRegistrarRota() {
		Rota rota = RotaMock.criarRota();

		doNothing().when(this.seletorRotas).registrarRota(any());

		this.seletorRotasService.registrarRota(rota);

		verify(this.seletorRotas, times(1)).registrarRota(any());
	}

}
