package br.gov.api.DefesaCivil.dto.informes;

public class RiosDTO {

	private String municipio;
	private String rio;
	private String estacao;
	private String status;
	private String leitura;
	private String monitoramento;
	
	public RiosDTO() {
		super();
	}

	public RiosDTO(String municipio, String rio, String estacao, String status, String leitura, String monitoramento) {
		super();
		this.municipio = municipio;
		this.rio = rio;
		this.estacao = estacao;
		this.status = status;
		this.leitura = leitura;
		this.monitoramento = monitoramento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getRio() {
		return rio;
	}

	public void setRio(String rio) {
		this.rio = rio;
	}

	public String getEstacao() {
		return estacao;
	}

	public void setEstacao(String estacao) {
		this.estacao = estacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLeitura() {
		return leitura;
	}

	public void setLeitura(String leitura) {
		this.leitura = leitura;
	}

	public String getMonitoramento() {
		return monitoramento;
	}

	public void setMonitoramento(String monitoramento) {
		this.monitoramento = monitoramento;
	}
	
	
}