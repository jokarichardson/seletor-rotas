package com.richardson.seletorrotas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardson.seletorrotas.logic.SeletorRotas;
import com.richardson.seletorrotas.model.Rota;

@Service
public class SeletorRotasService {
	@Autowired
	private SeletorRotas seletorRotas;

	private boolean qqrBoolean;

	public List<Rota> recuperarRotas() {
		this.qqrBoolean = true;
		return this.seletorRotas.recuperarRotas();
	}

	public String recuperarMelhorRota(String origem, String destino) {
		return this.seletorRotas.recuperarMelhorRota(origem, destino);
	}

	public void registrarRotas(List<Rota> rotaList) {
		this.seletorRotas.registrarRotas(rotaList);
	}
}
