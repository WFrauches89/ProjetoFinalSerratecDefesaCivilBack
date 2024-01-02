package br.gov.api.DefesaCivil.dto.PontoDeApoio;

import java.util.List;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioResponseDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;

public class PontoDeApoioResponseDTO extends PontoDeApoioBaseDTO {

	private Long id;
	private String data_cadastro;
	private Boolean ocultar;
	private List<TelefonePontoDeApoioResponseDTO> numerosTelefone;
	private UsuarioResponseDTO usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<TelefonePontoDeApoioResponseDTO> getNumerosTelefone() {
		return numerosTelefone;
	}

	public void setNumerosTelefone(List<TelefonePontoDeApoioResponseDTO> numerosTelefone) {
		this.numerosTelefone = numerosTelefone;
	}

	public UsuarioResponseDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioResponseDTO usuario) {
		this.usuario = usuario;
	}

}
