package br.gov.api.DefesaCivil.dto.usuario;

import br.gov.api.DefesaCivil.entities.ETipoPerfil;

public class UsuarioResponseDTO extends UsuarioBaseDTO {
    
    private Long id;
    private String dtCadastro;
    private ETipoPerfil perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(String dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	
    public ETipoPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(ETipoPerfil perfil) {
        this.perfil = perfil;
    }
}