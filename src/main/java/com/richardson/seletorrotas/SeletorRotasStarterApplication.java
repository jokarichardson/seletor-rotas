package com.richardson.seletorrotas;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.richardson.seletorrotas.application.ConsoleApplication;
import com.richardson.seletorrotas.application.WebApplication;

@SpringBootApplication
public class SeletorRotasStarterApplication {
	public static void main(String[] args) {
		SpringApplicationBuilder app = new SpringApplicationBuilder();

		switch (args.length) {
		case 0:
			System.out.println(
					"Não foi informado o arquivo para carga de rotas. Será utilizado ARQUIVO INTERNO, interface REST");
			app.sources(SeletorRotasStarterApplication.class, WebApplication.class);
			break;
		case 1:
			System.out.println("Não foi informado parâmetro para interface REST. Iniciando interface CONSOLE...");
			app.sources(ConsoleApplication.class);
			app.web(WebApplicationType.NONE);
			break;
		default:
			System.out.println("Iniciando aplicação REST");
			app.sources(SeletorRotasStarterApplication.class, WebApplication.class);
		}

		app.run(args);
	}
}