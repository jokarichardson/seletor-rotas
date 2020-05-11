package com.richardson.seletorrotas.support;

import static org.junit.Assert.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.mocks.RotaMock;
import com.richardson.seletorrotas.model.Rota;

@RunWith(SpringRunner.class)
public class RotaCSVFileHelperTest {

	@Test
	public void deveLerArquivoCSVDeRotas() {
		Path diretorio = Paths.get("src", "test", "resources", "csv");
		String arquivoRotas = diretorio.toString().concat("\\input-routes.csv");
		List<Rota> actualRotasList = RotaCSVFileHelper.lerArquivo(arquivoRotas);
		assertNotNull(actualRotasList);
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoLerArquivo() {
		Path diretorio = Paths.get("src", "test", "resources", "csv");
		String arquivoRotas = diretorio.toString().concat("\\inputs-routes.csv");
		RotaCSVFileHelper.lerArquivo(arquivoRotas);
	}

	@Test
	public void deveGravarArquivoCSVDeRotas() {
		List<Rota> rotasList = RotaMock.criarRotaList();
		Path diretorio = Paths.get("src", "test", "resources", "csv");
		String arquivoRotas = diretorio.toString()
				.concat("\\input-routes".concat(Integer.toString(Instant.now().getNano())).concat(".csv"));
		RotaCSVFileHelper.gravarArquivo(arquivoRotas, rotasList);
		RotaCSVFileHelper.removerArquivo(arquivoRotas);
	}

	@Test(expected = SeletorRotasGenericException.class)
	public void deveLancarExcecaoAoGravarArquivo() {
		List<Rota> rotasList = RotaMock.criarRotaList();
		Path diretorio = Paths.get("src", "test", "resources", "csv");
		String arquivoRotas = diretorio.toString();
		RotaCSVFileHelper.gravarArquivo(arquivoRotas, rotasList);
	}
}
