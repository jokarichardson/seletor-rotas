package com.richardson.seletorrotas.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richardson.seletorrotas.model.dto.Rota;
import com.richardson.seletorrotas.service.SeletorRotasService;
import com.richardson.seletorrotas.support.ResponseUtils;

@RestController
@RequestMapping(value = "/seletorrotas")
@Validated
public class SeletorRotasController {

	private static final Logger log = LoggerFactory.getLogger(SeletorRotasController.class);

	@Autowired
	private SeletorRotasService seletorRotasService;

	@GetMapping(value = "/rotas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> recuperarRotas() {
		log.info("recuperarRotas()");

		try {
			return new ResponseEntity<>(this.seletorRotasService.recuperarRotas(), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Não foi possível atender a requisição", ex);
			return new ResponseEntity<>(
					ResponseUtils.criaResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
							ex.getMessage(), ex.getClass().getSimpleName(), "/seletorrotas/rotas"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/melhor-rota", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> recuperarMelhorRota(
			@RequestParam @Size(max = 3, min = 3, message = "Origem deve ser informado e ter 3 caracteres. Ex: GRU") String origem,
			@RequestParam @Size(max = 3, min = 3, message = "Destino deve ser informado e ter 3 caracteres. Ex: GRU") String destino) {
		log.info("recuperarMelhorRota()");

		try {
			return new ResponseEntity<>(this.seletorRotasService.recuperarMelhorRota(origem, destino), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Não foi possível atender a requisição", ex);
			return new ResponseEntity<>(
					ResponseUtils.criaResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
							ex.getMessage(), ex.getClass().getSimpleName(), "/seletorrotas/melhor-rota"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/rotas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrarRota(@RequestBody @Valid Rota rota) {
		log.info("registrarRota({})", rota.toString());

		try {
			this.seletorRotasService.registrarRota(rota);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Não foi possível atender a requisição", ex);
			return new ResponseEntity<>(
					ResponseUtils.criaResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
							ex.getMessage(), ex.getClass().getSimpleName(), "/seletorrotas/rotas"),
					HttpStatus.BAD_REQUEST);
		}
	}
}
