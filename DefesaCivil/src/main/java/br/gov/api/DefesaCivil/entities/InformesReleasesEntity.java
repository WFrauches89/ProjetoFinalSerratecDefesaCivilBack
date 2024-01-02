package br.gov.api.DefesaCivil.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "informativo")
public class InformesReleasesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String dataEvento;
	private String tipo;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String dataPostagem;
	private Boolean ativo;
	private String dataEdicao;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioEntity usuario;

	public InformesReleasesEntity() {
		super();
	}

	public InformesReleasesEntity(Integer id, String titulo, String dataEvento, String tipo, String descricao,
			String dataPostagem, Boolean ativo, String dataEdicao, UsuarioEntity usuario) {
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

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "InformesReleasesEntity [id=" + id + ", titulo=" + titulo + ", dataEvento=" + dataEvento + ", tipo="
				+ tipo + ", descricao=" + descricao + ", dataPostagem=" + dataPostagem + ", ativo=" + ativo
				+ ", dataEdicao=" + dataEdicao + ", usuario=" + usuario + "]";
	}
	
}