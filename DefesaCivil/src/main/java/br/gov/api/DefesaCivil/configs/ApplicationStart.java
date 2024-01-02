package br.gov.api.DefesaCivil.configs;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioRequestDTO;
import br.gov.api.DefesaCivil.entities.ETipoPerfil;
import br.gov.api.DefesaCivil.repositories.UsuarioRepository;
import br.gov.api.DefesaCivil.services.UsuarioService;

@Component
public class ApplicationStart {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostConstruct
    public void carregarDadosIniciais() {
        if (usuarioRepository.count() == 0) {
        	usuarioService.adicionar(userMaster());
        }
    }
	
	private UsuarioRequestDTO userMaster() {
		UsuarioRequestDTO masterUser = new UsuarioRequestDTO();
        masterUser.setNomeUsuario("MichaelVieira");
        masterUser.setEmail("admin@admin.com");
        masterUser.setSenha("admin");
        
        return masterUser;

    }

}
