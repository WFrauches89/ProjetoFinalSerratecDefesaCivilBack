package br.gov.api.DefesaCivil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.api.DefesaCivil.dto.PontoDeApoio.PontoDeApoioRequestDTO;
import br.gov.api.DefesaCivil.dto.PontoDeApoio.PontoDeApoioResponseDTO;
import br.gov.api.DefesaCivil.services.PontoDeApoioService;

@RestController
@RequestMapping("/pontosdeapoio")
public class PontoDeApoioController {

	@Autowired
	private PontoDeApoioService pontosDeApoioService;

	@GetMapping
	public ResponseEntity<List<PontoDeApoioResponseDTO>> listarTodosPontosDeApoio() {
		return ResponseEntity.ok(pontosDeApoioService.listarTodosPontosDeApoio());
	}

	@PostMapping
	public ResponseEntity<PontoDeApoioResponseDTO> cadastrarPontosDeApoio(
			@RequestBody PontoDeApoioRequestDTO pontosDeApoioRequest) {
		return ResponseEntity.status(201).body(pontosDeApoioService.cadastrarPontosDeApoio(pontosDeApoioRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PontoDeApoioResponseDTO> obterPontoDeApoioPorId(@PathVariable Long id) {
		return ResponseEntity.ok(pontosDeApoioService.obterPontoDeApoioPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PontoDeApoioResponseDTO> editarPontoDeApoio(@PathVariable Long id,
			@RequestBody PontoDeApoioRequestDTO pontosDeApoioRequest) {
		return ResponseEntity.status(200).body(pontosDeApoioService.editarPontoDeApoio(id, pontosDeApoioRequest));
	}

	@PatchMapping("/alterarStatus/{id}")
	public ResponseEntity<PontoDeApoioResponseDTO> alterarStatus(@PathVariable Long id, @RequestParam Long idUsuario) {
		System.out.println(idUsuario);
		return ResponseEntity.status(200).body(pontosDeApoioService.alterarStatus(id, idUsuario));
	}

	@PatchMapping("/removerPontosDeApoio/{id}")
	public ResponseEntity<PontoDeApoioResponseDTO> removerPontosDeApoio(@PathVariable Long id, @RequestParam Long idUsuario) {
		System.out.println(idUsuario);
		return ResponseEntity.status(200).body(pontosDeApoioService.removerPontosDeApoio(id, idUsuario));
	}

}
