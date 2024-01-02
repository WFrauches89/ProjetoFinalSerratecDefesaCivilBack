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
@Table(name = "telefonePontoDeApoio")
public class TelefonePontoDeApoioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNumeros")
	private Long id;
	
	@Column(nullable = true)
	private String numero;
	
	@ManyToOne
    @JoinColumn(name = "idPontoDeApoio")
    private PontoDeApoioEntity pontoDeApoio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public PontoDeApoioEntity getPontoDeApoio() {
		return pontoDeApoio;
	}

	public void setPontoDeApoio(PontoDeApoioEntity pontoDeApoio) {
		this.pontoDeApoio = pontoDeApoio;
	}
}
