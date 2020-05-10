package com.richardson.seletorrotas.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.richardson.seletorrotas.model.Rota;

@Configuration
public class RotasBeanConfiguration {
	@Bean
	public List<Rota> rotas() {

		List<Rota> rotas = new ArrayList<>();
		rotas.add(new Rota("GRU", "BRC", 10));
		rotas.add(new Rota("BRC", "SCL", 5));
		rotas.add(new Rota("GRU", "CDG", 75));
		rotas.add(new Rota("GRU", "SCL", 20));
		rotas.add(new Rota("GRU", "ORL", 56));
		rotas.add(new Rota("ORL", "CDG", 8));
		rotas.add(new Rota("SCL", "ORL", 20));
		rotas.add(new Rota("BRC", "CDG", 35));

		return rotas;
	}
}
