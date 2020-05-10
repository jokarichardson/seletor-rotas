package com.richardson.seletorrotas.model;

import java.text.MessageFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "origem", "destino", "custo" })
public class Rota {

	@NotNull
	@Size(max = 3, min = 3, message = "Origem deve ser informado e ter 3 caracteres. Ex: GRU")
	private String origem;

	@NotNull
	@Size(max = 3, min = 3, message = "Destino deve ser informado e ter 3 caracteres. Ex: GRU")
	private String destino;

	@NotNull
	@Min(value = 1, message = "Custo da rota deve ser superior a zero")
	private Integer custo;

	public Rota() {
		super();
	}

	public Rota(String origem, String destino, Integer custo) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.custo = custo;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getCusto() {
		return custo;
	}

	public void setCusto(Integer custo) {
		this.custo = custo;
	}

	@Override
	public String toString() {
		return MessageFormat.format("'{' origem: {0}, destino: {1}, custo: {2}'}'", this.origem, this.destino,
				this.custo);
	}
}
