package com.richardson.seletorrotas.model;

import java.text.MessageFormat;
import java.util.Map;

public class Rotas implements Comparable<Rotas> {
	private String origem;
	private Map<String, Integer> destinos;

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Map<String, Integer> getDestinos() {
		return destinos;
	}

	public void setDestinos(Map<String, Integer> destinos) {
		this.destinos = destinos;
	}

	@Override
	public int compareTo(Rotas o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return MessageFormat.format("'{' origem: {0}, destinos: {1}'}'", this.origem, this.destinos);
	}

}
