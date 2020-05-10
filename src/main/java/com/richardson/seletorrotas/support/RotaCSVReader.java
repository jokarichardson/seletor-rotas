package com.richardson.seletorrotas.support;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.model.Rota;

@Component
public class RotaCSVReader {
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
}
