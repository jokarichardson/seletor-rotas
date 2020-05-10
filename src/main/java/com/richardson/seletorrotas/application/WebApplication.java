package com.richardson.seletorrotas.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

import com.richardson.seletorrotas.logic.SeletorRotas;
import com.richardson.seletorrotas.support.RotaCSVReader;

@Component
@ConditionalOnWebApplication
public class WebApplication implements CommandLineRunner {

	@Autowired
	private SeletorRotas seletorRotas;

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			this.seletorRotas.rotas = RotaCSVReader.lerArquivo(args[0]);
		}

	}

}
