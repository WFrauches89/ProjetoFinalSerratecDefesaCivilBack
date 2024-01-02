package br.gov.api.DefesaCivil.dto.PontoDeApoio;

public abstract class PontoDeApoioBaseDTO {
	private String ponto_apoio;
	private String bairro;
	private Boolean status;
	private String endereco;
	private String localizacao;
	private String dataEdicao;

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

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(String dataEdicao) {
		this.dataEdicao = dataEdicao;
	}
}
