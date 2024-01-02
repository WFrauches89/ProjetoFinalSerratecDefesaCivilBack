package br.gov.api.DefesaCivil.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.api.DefesaCivil.dto.QuemSomos.QuemSomosRequestDTO;
import br.gov.api.DefesaCivil.dto.QuemSomos.QuemSomosResponseDTO;
import br.gov.api.DefesaCivil.services.QuemSomosService;

@RestController
@RequestMapping("/quemsomos")
@CrossOrigin(origins = "*")
public class QuemSomosController {
	
	@Autowired
	private QuemSomosService quemSomosService;
	
	@GetMapping
	public ResponseEntity<List<QuemSomosResponseDTO>> listarTodosQuemSomos(){
		return ResponseEntity.ok(quemSomosService.listarTodosQuemSomos());
	}
	
	@PostMapping
	public ResponseEntity<QuemSomosResponseDTO> cadastrarQuemSomos(@RequestBody QuemSomosRequestDTO quemSomosRequest){
		return ResponseEntity.status(201).body(quemSomosService.cadastrarQuemSomos(quemSomosRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<QuemSomosResponseDTO> obterQuemSomosPorId(@PathVariable Long id){
		return ResponseEntity.ok(quemSomosService.obterQuemSomosPorId(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<QuemSomosResponseDTO> editarQuemSomos(@PathVariable Long id, @RequestBody QuemSomosRequestDTO quemSomosRequest){
		return ResponseEntity.status(200).body(quemSomosService.editarQuemSomos(id, quemSomosRequest));
	}
	
}
