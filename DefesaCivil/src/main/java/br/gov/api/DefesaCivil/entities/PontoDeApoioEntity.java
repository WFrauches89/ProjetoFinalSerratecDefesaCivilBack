package br.gov.api.DefesaCivil.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import br.gov.api.DefesaCivil.common.ConversorData;

@Entity
@Table(name = "pontoDeApoio")
public class PontoDeApoioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPontoDeApoio")
	private Long id;
	
	@Column(nullable = false)
	private String ponto_apoio;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private Boolean status;
	
	@Column(nullable = false)
	private String endereco;
	
	@Column(nullable = false)
	private String data_cadastro;
	
	@Column(nullable = false)
	private Boolean ocultar;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String localizacao;
	
	@OneToMany(mappedBy = "pontoDeApoio")
    private List<TelefonePontoDeApoioEntity> numerosTelefone;
	
	@Column(nullable = false)
	private String dataEdicao;
	
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioEntity usuario;
	
	public PontoDeApoioEntity(){
		this.data_cadastro = ConversorData.converterDateParaDataHora(new Date());
		this.ocultar = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPonto_apoio() {
		return ponto_apoio;
	}

	public void setPonto_apoio(String ponto_apoio) {
		this.ponto_apoio = ponto_apoio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(String data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Boolean getOcultar() {
		return ocultar;
	}

	public void setOcultar(Boolean ocultar) {
		this.ocultar = ocultar;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public List<TelefonePontoDeApoioEntity> getNumerosTelefone() {
		return numerosTelefone;
	}

	public void setNumerosTelefone(List<TelefonePontoDeApoioEntity> numerosTelefone) {
		this.numerosTelefone = numerosTelefone;
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
	
}
