package com.richardson.seletorrotas.support;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.model.Rota;

@RunWith(SpringRunner.class)
public class RotaCSVReaderTest {

	@Test
	public void deveLerArquivoCSVDeRotas() {
		File arquivoRotas = new File("input-routes.csv");
		List<Rota> actualRotasList = RotaCSVReader.lerArquivo(arquivoRotas);

	}
}
