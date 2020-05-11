package com.richardson.seletorrotas.application;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.util.ObjectUtils;

import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.logic.SeletorRotas;
import com.richardson.seletorrotas.support.RotaCSVFileHelper;

@ConditionalOnNotWebApplication
public class ConsoleApplication implements CommandLineRunner {

	private static final String ENTRADA_INVALIDA = "Entrada inválida";

	private SeletorRotas seletorRotas;

	public ConsoleApplication() {
		this.seletorRotas = new SeletorRotas();
	}

	public void run(String... args) throws Exception {
		this.alimentarRotas(args[0]);

		String entrada = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n\n");
			System.out.println("Para sair digite SAIR");
			System.out.print("Por favor, informe a rota desejada (Ex.: ORI-DES): ");
			entrada = scanner.nextLine();

			if ("sair".equalsIgnoreCase(entrada))
				break;

			if (this.validarEntrada(entrada)) {
				String origem = entrada.substring(0, 3);
				String destino = entrada.substring(4, 7);
				try {
					System.out.println("Melhor Rota: ".concat(this.seletorRotas.recuperarMelhorRota(origem, destino)));
					System.out.println("\n\n");
				} catch (SeletorRotasGenericException s) {
					System.out.println("Ocorreu um erro: ");
					System.out.println(s.getMessage());
					System.out.println("\n\n");
				}
			}
		}
		scanner.close();
	}

	private void alimentarRotas(String arquivoRotas) {
		this.seletorRotas.rotas = RotaCSVFileHelper.lerArquivo(arquivoRotas);
	}

	private Boolean validarEntrada(String entrada) {
		if (ObjectUtils.isEmpty(entrada)) {
			System.out.println(ENTRADA_INVALIDA);
			return false;
		}

		if (!entrada.contains("-")) {
			System.out.println(ENTRADA_INVALIDA);
			return false;
		}

		if (entrada.length() != 7) {
			System.out.println(ENTRADA_INVALIDA);
			return false;
		}

		return true;
	}

}
