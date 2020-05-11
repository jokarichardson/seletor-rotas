package com.richardson.seletorrotas.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.model.Rota;

@Component
public class RotaCSVFileHelper {
	public static List<Rota> lerArquivo(String arquivo) {
		MappingIterator<Rota> rotaIterator;

		try {
			File arquivoCSV = new File(arquivo);
			rotaIterator = new CsvMapper().readerWithTypedSchemaFor(Rota.class).readValues(arquivoCSV);
			return rotaIterator.readAll();
		} catch (IOException e) {
			throw new SeletorRotasGenericException(e.getMessage());
		}
	}

	public static void gravarArquivo(String arquivo, List<Rota> rotas) {
		try {
			File arq = new File(arquivo);
			arq.createNewFile();
			FileOutputStream arquivoCSV = new FileOutputStream(arq, true);
			PrintWriter printWriter = new PrintWriter(arquivoCSV);
			rotas.forEach(rota -> printWriter.append(converteParaCSV(rota)));
			printWriter.close();
			arquivoCSV.close();
		} catch (IOException e) {
			throw new SeletorRotasGenericException(e.getMessage());
		}
	}

	public static void removerArquivo(String arquivo) {
		File arq = new File(arquivo);
		arq.delete();
	}

	private static String converteParaCSV(Rota rota) {
		return MessageFormat.format("\n{0},{1},{2}", rota.getOrigem(), rota.getDestino(), rota.getCusto());
	}
}
