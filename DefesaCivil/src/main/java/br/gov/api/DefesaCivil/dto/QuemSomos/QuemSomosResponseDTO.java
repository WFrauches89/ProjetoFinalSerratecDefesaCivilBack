package br.gov.api.DefesaCivil.dto.QuemSomos;

import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;

public class QuemSomosResponseDTO {

    private Long id;
    private String endereco;
    private String telefone;
    private String email;
    private String horarioAtendimento;
    private String secretario;
    private String descricao;
    private String bairro;
	String dataEdicao;
	UsuarioResponseDTO usuario;
	
	public QuemSomosResponseDTO() {
		super();
	}

	public QuemSomosResponseDTO(Long id, String endereco, String telefone, String email, String horarioAtendimento,
			String secretario, String descricao, String bairro, String dataEdicao, UsuarioResponseDTO usuario) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.horarioAtendimento = horarioAtendimento;
		this.secretario = secretario;
		this.descricao = descricao;
		this.bairro = bairro;
		this.dataEdicao = dataEdicao;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(String horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public String getSecretario() {
		return secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(String dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public UsuarioResponseDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResponseDTO usuario) {
		this.usuario = usuario;
	}
	
}