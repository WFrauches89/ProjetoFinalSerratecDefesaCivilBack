package br.gov.api.DefesaCivil.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioLoginRequestDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioLoginResponseDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioRequestDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceBadRequestException;
import br.gov.api.DefesaCivil.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('MASTER')")
	public ResponseEntity<List<UsuarioResponseDTO>> obterTodos(){
		return ResponseEntity.ok(usuarioService.obterTodos());
	}

	@GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('MASTER')")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Long id){
		return ResponseEntity.ok(usuarioService.obterPorId(id));
	}

	@GetMapping("/email/{email}")
	@PreAuthorize("hasAuthority('MASTER')")
	public ResponseEntity<UsuarioResponseDTO> obterPorEmail(@PathVariable String email){
		return ResponseEntity.ok(usuarioService.obterPorEmail(email));
	}

	@PostMapping
	@PreAuthorize("hasAuthority('MASTER')")
	public ResponseEntity<UsuarioResponseDTO> adicionar(@RequestBody UsuarioRequestDTO usuarioRequest){

		if(usuarioRequest.getNomeUsuario() == null || usuarioRequest.getNomeUsuario().length() < 1){
			throw new  ResourceBadRequestException("Nome do usuario não pode estar vazio.");
		}

		if(usuarioRequest.getEmail() == null || usuarioRequest.getEmail().length() < 1){
			throw new  ResourceBadRequestException("E-mail não pode estar vazio.");
		}

		if(usuarioRequest.getSenha() == null || usuarioRequest.getSenha().length() < 1){
			throw new  ResourceBadRequestException("Senha não pode estar vazio.");
		} else if(usuarioRequest.getSenha().length() > 20){
			throw new  ResourceBadRequestException("Limite de caracteres excedido, MAX:20");
		}

		return ResponseEntity.status(201).body(usuarioService.adicionar(usuarioRequest));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequest){
		if(usuarioRequest.getNomeUsuario() == null || usuarioRequest.getNomeUsuario().length() < 1){
			throw new  ResourceBadRequestException("Nome do usuario não pode estar vazio.");
		}

		if(usuarioRequest.getEmail() == null || usuarioRequest.getEmail().length() < 1){
			throw new  ResourceBadRequestException("E-mail não pode estar vazio.");
		}

		if(usuarioRequest.getSenha() != null && usuarioRequest.getSenha().length() > 20){
			throw new  ResourceBadRequestException("Limite de caracteres excedido, MAX:20");
		}

		return ResponseEntity.status(200).body(usuarioService.atualizar(id, usuarioRequest));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('MASTER')")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		usuarioService.deletar(id);
		return ResponseEntity.status(204).build();
	}
	
	@PatchMapping("alterarsenha/{id}")
	public ResponseEntity<UsuarioResponseDTO> editarSenha(@PathVariable Long id, @RequestBody String novaSenha){
		if(novaSenha == null || novaSenha.length() < 1){
			throw new  ResourceBadRequestException("Senha não pode estar vazio.");
		} else if(novaSenha.length() > 20){
			throw new  ResourceBadRequestException("Limite de caracteres excedido, MAX:20");
		}
		
		return ResponseEntity.status(200).body(usuarioService.editarSenha(id, novaSenha));
	}
	
	@PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponseDTO> logar(@RequestBody UsuarioLoginRequestDTO usuariologinRequest){
        UsuarioLoginResponseDTO usuarioLogado = usuarioService.logar(usuariologinRequest.getEmail(), usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(usuarioLogado);
    }
	
	@PostMapping("/verificarsenhaatual")
    public ResponseEntity<Boolean> verificarSenhaAtual(@RequestBody UsuarioLoginRequestDTO usuariologinRequest){
        Boolean verificado = usuarioService.verificarSenhaAtual(usuariologinRequest.getEmail(), usuariologinRequest.getSenha());
        return ResponseEntity.status(200).body(verificado);
    }
}
