package com.richardson.seletorrotas.support;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.model.Rota;

public class RotaCSVReader {
	public static List<Rota> lerArquivo(File arquivoCSV) {
		MappingIterator<Rota> rotaIterator;
		try {
			rotaIterator = new CsvMapper().readerWithTypedSchemaFor(Rota.class).readValues(arquivoCSV);
			return rotaIterator.readAll();
		} catch (IOException e) {
			throw new SeletorRotasGenericException(e.getMessage());
		}
	}
}
