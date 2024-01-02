package br.gov.api.DefesaCivil.dto.PontoDeApoio;

import java.util.List;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioRequestDTO;

public class PontoDeApoioRequestDTO extends PontoDeApoioBaseDTO{
	
	private List<TelefonePontoDeApoioRequestDTO> numerosTelefone;
	private Long usuarioId;

	public List<TelefonePontoDeApoioRequestDTO> getNumerosTelefone() {
		return numerosTelefone;
	}

	public void setNumerosTelefone(List<TelefonePontoDeApoioRequestDTO> numerosTelefone) {
		this.numerosTelefone = numerosTelefone;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}	
}
