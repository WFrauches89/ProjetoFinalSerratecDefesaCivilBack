package br.gov.api.DefesaCivil.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioLoginResponseDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioRequestDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;
import br.gov.api.DefesaCivil.entities.ETipoPerfil;
import br.gov.api.DefesaCivil.entities.UsuarioEntity;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceBadRequestException;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceNotFoundException;
import br.gov.api.DefesaCivil.repositories.UsuarioRepository;
import br.gov.api.DefesaCivil.security.JWTService;

@Service
public class UsuarioService {
	
	private static final String BEARER = "Bearer ";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
    private JWTService jwtService;

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UsuarioResponseDTO> obterTodos(){
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();

		return usuarios
			.stream()
			.map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
			.collect(Collectors.toList());	
	}
	
	public UsuarioResponseDTO obterPorId(Long id){
		Optional<UsuarioEntity> optUsuario = usuarioRepository.findById(id);
		
		if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO adicionar(UsuarioRequestDTO usuarioRequest){
		uniqueEMAIL(usuarioRequest, 0L);
		UsuarioEntity usuarioModel = mapper.map(usuarioRequest, UsuarioEntity.class);
		String senha =  passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(senha);
		if (usuarioRepository.count() == 0) {
			usuarioModel.setPerfil(ETipoPerfil.MASTER);
		}
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO usuarioRequest){
		uniqueEMAIL(usuarioRequest, id);
		UsuarioResponseDTO obterperfil = obterPorId(id);
		UsuarioEntity usuarioModel = mapper.map(usuarioRequest, UsuarioEntity.class);
		if(usuarioModel.getSenha() == null) {
			UsuarioEntity obterSenhaAntiga = usuarioRepository.findById(id).get();
			usuarioModel.setSenha(obterSenhaAntiga.getSenha());
		}else {
			String senha =  passwordEncoder.encode(usuarioModel.getSenha());
			usuarioModel.setSenha(senha);
		}
		usuarioModel.setId(id);
		usuarioModel.setPerfil(obterperfil.getPerfil());
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		usuarioRepository.deleteById(id);
	}

	public UsuarioResponseDTO obterPorEmail(String email){
        Optional<UsuarioEntity> optUsuario =  usuarioRepository.findByEmail(email);
        
		if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("Nenhum registro encontrado para o email: " + email);
        }
		
        return mapper.map(optUsuario.get(),UsuarioResponseDTO.class);
    }

    
	public void uniqueEMAIL(UsuarioRequestDTO usuarioRequest, Long id){
		List<UsuarioResponseDTO> listaUsuarioResponse = obterTodos();

		for (UsuarioResponseDTO usuarioResponse : listaUsuarioResponse){
			if(usuarioResponse.getEmail().equals(usuarioRequest.getEmail()) && usuarioResponse.getId() != id){
				throw new ResourceBadRequestException("E-mail já cadastrado!");
			}
		}
	}
	
	public UsuarioResponseDTO editarSenha(Long id, String novaSenha){
		UsuarioEntity usuarioModel = mapper.map(obterPorId(id), UsuarioEntity.class);
		String senha =  passwordEncoder.encode(novaSenha);
		usuarioModel.setSenha(senha);
		usuarioModel = usuarioRepository.save(usuarioModel);
		
		return mapper.map(usuarioModel, UsuarioResponseDTO.class);
	}
	
	public UsuarioLoginResponseDTO logar(String email, String senha){
		try{
			Authentication autenticacao = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
		
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
			String token =  BEARER + jwtService.gerarToken(autenticacao);
			UsuarioResponseDTO usuarioResponse = obterPorEmail(email);
			return new UsuarioLoginResponseDTO(token, usuarioResponse);

		} catch (RuntimeException e){
			throw new ResourceBadRequestException("E-mail ou senha incorretos.");
		}
        
    }
	
	public Boolean verificarSenhaAtual(String email, String senha){
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha,Collections.emptyList()));
			return true;

		} catch (RuntimeException e){
			return false;
		}
        
    }
}
