package br.gov.api.DefesaCivil.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.api.DefesaCivil.dto.informes.InformesReleasesRequest;
import br.gov.api.DefesaCivil.dto.informes.InformesReleasesResponse;
import br.gov.api.DefesaCivil.dto.informes.RiosDTO;
import br.gov.api.DefesaCivil.services.InformesReleasesService;

@RestController
@RequestMapping("/informativo")
public class InformesReleasesController {

	@Autowired
	InformesReleasesService informesReleasesService;

	@GetMapping("/estagioOperacional")
	public String estagioOperacional()throws IOException {
		return informesReleasesService.estagioOperacional();
	}

	@PostMapping("/cadastrar")
	public InformesReleasesResponse cadastar(@RequestBody InformesReleasesRequest informes) {
		return informesReleasesService.cadastar(informes);
	}

	@GetMapping("/{id}")
	public InformesReleasesResponse acharId(@PathVariable Integer id) {
		return informesReleasesService.acharId(id);
	}

	@GetMapping("/listarInformes")
	public List<InformesReleasesResponse> listarInformes() {
		return informesReleasesService.listarInformes();
	}

	@PutMapping("/atualizar/{id}")
	public InformesReleasesResponse atualizar(@PathVariable Integer id, @RequestBody InformesReleasesRequest informes) {
		return informesReleasesService.atualizar(id, informes);
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id, @RequestParam Long usuarioId) {
		informesReleasesService.deletar(id, usuarioId);
	}

	@GetMapping("/rios")
	public List<RiosDTO> relatoriosRios() throws IOException {
		return informesReleasesService.relatoriosRios();
	}
}