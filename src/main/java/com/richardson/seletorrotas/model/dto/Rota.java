package com.richardson.seletorrotas.model.dto;

import java.text.MessageFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Rota {

	@NotNull
	@Size(max = 3, min = 3, message = "Aeroporto Origem deve ser informado e ter 3 caracteres. Ex: GRU")
	private String aeroportoOrigem;

	@NotNull
	@Size(max = 3, min = 3, message = "Aeroporto Destino deve ser informado e ter 3 caracteres. Ex: GRU")
	private String aeroportoDestino;

	@NotNull
	@Min(value = 1, message = "Custo da rota deve ser superior a zero")
	private Integer custo;

	public Rota() {
		super();
	}

	public Rota(String aeroportoOrigem, String aerportoDestino, Integer custo) {
		super();
		this.aeroportoOrigem = aeroportoOrigem;
		this.aeroportoDestino = aerportoDestino;
		this.custo = custo;
	}

	public String getAeroportoOrigem() {
		return aeroportoOrigem;
	}

	public void setAeroportoOrigem(String aeroportoOrigem) {
		this.aeroportoOrigem = aeroportoOrigem;
	}

	public String getAeroportoDestino() {
		return aeroportoDestino;
	}

	public void setAeroportoDestino(String aeroportoDestino) {
		this.aeroportoDestino = aeroportoDestino;
	}

	public Integer getCusto() {
		return custo;
	}

	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	@Override
	public String toString() {
		return MessageFormat.format("'{' aeroportoOrigem: {0}, aeroportoDestino: {1}, custo: {2}'}'",
				this.aeroportoOrigem, this.aeroportoDestino, this.custo);
	}
}
