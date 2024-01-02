package br.gov.api.DefesaCivil.dto.informes;

public class InformesReleasesRequest {

	String titulo;
	String dataEvento;
	String tipo;
	String descricao;
	Long usuarioId;

	public InformesReleasesRequest() {
		super();
	}

	public InformesReleasesRequest(String titulo, String dataEvento, String tipo, String descricao, Long usuarioId) {
		super();
		this.titulo = titulo;
		this.dataEvento = dataEvento;
		this.tipo = tipo;
		this.descricao = descricao;
		this.usuarioId = usuarioId;
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

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}