package com.richardson.seletorrotas.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.richardson.seletorrotas.exception.SeletorRotasGenericException;
import com.richardson.seletorrotas.model.dto.Rota;
import com.richardson.seletorrotas.support.MessageUtils;

@Component
public class SeletorRotas {

	private static final String SEPARADOR_ROTAS = " - ";

	private List<Rota> rotas;

	private MessageUtils messageUtils;

	private Map<String, Map<String, Integer>> mapaRotas;
	private Map<Integer, String> rotasEncontradas = new HashMap<>();
	private Integer custo = Integer.valueOf(0);
	private StringBuilder sbRota = new StringBuilder();

	@Autowired
	public SeletorRotas(List<Rota> rotas, MessageUtils messageUtils) {
		this.rotas = rotas;
		this.messageUtils = messageUtils;
	}

	public List<Rota> recuperarRotas() {
		if (CollectionUtils.isEmpty(rotas)) {
			throw new SeletorRotasGenericException(this.messageUtils.get("msg.empty.route.list"));
		}

		return this.rotas;
	}

	public void registrarRota(Rota rota) {
		this.rotas.add(rota);
	}

	public String recuperarMelhorRota(String origem, String destino) {
		if (CollectionUtils.isEmpty(rotas)) {
			throw new SeletorRotasGenericException(this.messageUtils.get("msg.empty.route.list"));
		}

		this.montarMapaRotas();
		this.avaliarOrigem(origem);
		this.caminharMapa(origem, destino);
		this.ordenarRotasEncontradas();

		String retorno;
		this.sbRota.delete(0, this.sbRota.length());

		if (this.rotasEncontradas.entrySet().iterator().hasNext()) {
			retorno = this.rotasEncontradas.entrySet().iterator().next().getValue();
			this.rotasEncontradas.clear();
		} else
			throw new SeletorRotasGenericException(this.messageUtils.get("msg.route.not.found"));

		return retorno;
	}

	private void montarMapaRotas() {
		Set<String> origens = new HashSet<>();

		this.mapaRotas = new HashMap<String, Map<String, Integer>>();

		this.rotas.forEach(rota -> {
			origens.add(rota.getOrigem());
		});

		origens.forEach(o -> {
			Map<String, Integer> destinos = new HashMap<>();

			List<Rota> destinosPorOrigem = this.rotas.stream().filter(r -> r.getOrigem().equalsIgnoreCase(o))
					.collect(Collectors.toList());

			destinosPorOrigem.forEach(d -> {
				destinos.put(d.getDestino(), d.getCusto());
			});

			this.mapaRotas.put(o, destinos);
		});
	}

	private void caminharMapa(String origem, String destino) {
		Map<String, Integer> destinos = this.mapaRotas.get(origem);

		if (!CollectionUtils.isEmpty(destinos)) {
			this.sbRota.append(origem.concat(SEPARADOR_ROTAS));
		} else {
			this.custo = Integer.valueOf(0);
			return;
		}

		for (Map.Entry<String, Integer> d : destinos.entrySet()) {
			this.custo += d.getValue();

			if (d.getKey().equalsIgnoreCase(destino)) {
				this.rotasEncontradas.put(this.custo, this.sbRota.toString().concat(destino));
				this.custo -= d.getValue();
			} else {
				this.caminharMapa(d.getKey(), destino);
			}
		}

		this.sbRota.delete(6, this.sbRota.length());
		this.custo = Integer.valueOf(0);
	}

	private void avaliarOrigem(String origem) {
		if (!this.mapaRotas.containsKey(origem))
			throw new SeletorRotasGenericException(this.messageUtils.get("msg.origin.not.found"));
	}

	private void ordenarRotasEncontradas() {
		this.rotasEncontradas = this.rotasEncontradas.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}
}
