package br.gov.api.DefesaCivil.dto.informes;

import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;

public class InformesReleasesResponse {

	Integer id;
	String titulo;
	String dataEvento;
	String tipo;
	String descricao;
	String dataPostagem;
	Boolean ativo;
	String dataEdicao;
	UsuarioResponseDTO usuario;

	public InformesReleasesResponse() {
		super();
	}

	public InformesReleasesResponse(Integer id, String titulo, String dataEvento, String tipo, String descricao,
			String dataPostagem, Boolean ativo, String dataEdicao, UsuarioResponseDTO usuario) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dataEvento = dataEvento;
		this.tipo = tipo;
		this.descricao = descricao;
		this.dataPostagem = dataPostagem;
		this.ativo = ativo;
		this.dataEdicao = dataEdicao;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(String dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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