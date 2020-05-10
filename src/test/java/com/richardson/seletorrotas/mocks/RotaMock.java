package com.richardson.seletorrotas.mocks;

import java.util.ArrayList;
import java.util.List;

import com.richardson.seletorrotas.model.Rota;

public class RotaMock {

	public static Rota criarRota() {
		return new Rota("GRU", "BRC", 10);
	}

	public static List<Rota> criarRotaList() {
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
