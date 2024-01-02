package br.gov.api.DefesaCivil.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.api.DefesaCivil.common.ConversorData;
import br.gov.api.DefesaCivil.dto.informes.InformesReleasesRequest;
import br.gov.api.DefesaCivil.dto.informes.InformesReleasesResponse;
import br.gov.api.DefesaCivil.dto.informes.RiosDTO;
import br.gov.api.DefesaCivil.dto.usuario.UsuarioResponseDTO;
import br.gov.api.DefesaCivil.entities.InformesReleasesEntity;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceNotFoundException;
import br.gov.api.DefesaCivil.repositories.InformesReleasesRepository;
import br.gov.api.DefesaCivil.repositories.UsuarioRepository;

@Service
public class InformesReleasesService {

	@Autowired
	InformesReleasesRepository informesReleasesRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	ModelMapper mapper;

	public InformesReleasesEntity parseDeInformesReleasesRequest(InformesReleasesRequest objeto) {
		InformesReleasesEntity informe = new InformesReleasesEntity();

		informe.setTitulo(objeto.getTitulo());
		informe.setDataEvento(objeto.getDataEvento());
		informe.setTipo(objeto.getTipo());
		informe.setDescricao(objeto.getDescricao());

		return informe;
	}

	public InformesReleasesResponse parseDeInformesReleasesResponse(InformesReleasesEntity objeto) {
		InformesReleasesResponse informe = new InformesReleasesResponse();

		informe.setAtivo(objeto.getAtivo());
		informe.setDataEvento(objeto.getDataEvento());
		informe.setDataPostagem(objeto.getDataPostagem());
		informe.setDescricao(objeto.getDescricao());
		informe.setTipo(objeto.getTipo());
		informe.setTitulo(objeto.getTitulo());
		informe.setDataEdicao(objeto.getDataEdicao());
		informe.setId(objeto.getId());
		UsuarioResponseDTO usu = mapper.map(usuarioRepository.getReferenceById(objeto.getUsuario().getId()),
				UsuarioResponseDTO.class);
		informe.setUsuario(usu);

		return informe;
	}

	public String estagioOperacional() throws IOException {
		Document doc = Jsoup.connect("https://web2.petropolis.rj.gov.br/dfc/gestao/BoletimMudancaEstagio.php")
				.timeout(6000).get();

		Elements body = doc.select("div#conteudo table tbody");
		Elements tabela = body.select("tr");
		System.out.println(tabela.size());
		String linha = tabela.select("td").get(2).text();
		System.out.println(linha);

		return linha;
	}

	public InformesReleasesResponse cadastar(InformesReleasesRequest informes) {
		InformesReleasesEntity informeNovo = parseDeInformesReleasesRequest(informes);

		informeNovo.setDataPostagem(ConversorData.converterDateParaDataHora(new Date()));
		informeNovo.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));

		informeNovo.setAtivo(true);

		informeNovo.setUsuario(usuarioRepository.getReferenceById(informes.getUsuarioId()));

		informesReleasesRepository.save(informeNovo);
		usuarioRepository.save(usuarioRepository.getReferenceById(informes.getUsuarioId()));

		return parseDeInformesReleasesResponse(informeNovo);
	}

	public InformesReleasesResponse acharId(Integer id) {
		InformesReleasesEntity informes = informesReleasesRepository.findById(id).get();
		if (informes == null) {

			throw new ResourceNotFoundException("Esse informe não existe");
		} else {
			InformesReleasesResponse informeResponse = parseDeInformesReleasesResponse(
					informesReleasesRepository.findById(id).get());

			return informeResponse;
		}
	}

	public List<InformesReleasesResponse> listarInformes() {
		List<InformesReleasesResponse> informesResposta = new ArrayList<>();
		List<InformesReleasesEntity> informes = informesReleasesRepository.findAll();
		for (InformesReleasesEntity informe : informes) {
			informesResposta.add(parseDeInformesReleasesResponse(informe));
		}
		return informesResposta;
	}

	public InformesReleasesResponse atualizar(Integer id, InformesReleasesRequest objeto) {
		if (informesReleasesRepository.findById(id).get() == null) {
			throw new ResourceNotFoundException("Esse informe não existe");
		} else {
			Optional<InformesReleasesEntity> registroAntigo = informesReleasesRepository.findById(id);
			InformesReleasesEntity informe = parseDeInformesReleasesRequest(objeto);

			if (informe.getTitulo() != null) {
				registroAntigo.get().setTitulo(informe.getTitulo());
			}
			if (informe.getDataEvento() != null) {
				registroAntigo.get().setDataEvento(informe.getDataEvento());
			}
			if (informe.getDescricao() != null) {
				registroAntigo.get().setDescricao(informe.getDescricao());
			}
			if (informe.getTipo() != null) {
				registroAntigo.get().setTipo(informe.getTipo());
			}
			if (informe.getDescricao() != null) {
				registroAntigo.get().setDescricao(informe.getDescricao());
			}

			registroAntigo.get().setUsuario(informe.getUsuario());
			registroAntigo.get().setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));

			registroAntigo.get().setUsuario(usuarioRepository.getReferenceById(objeto.getUsuarioId()));

			informesReleasesRepository.save(registroAntigo.get());
			usuarioRepository.save(usuarioRepository.getReferenceById(objeto.getUsuarioId()));

			return parseDeInformesReleasesResponse(registroAntigo.get());

		}
	}

	public void deletar(Integer id, Long ususarioId) {
		InformesReleasesEntity informe = informesReleasesRepository.findById(id).get();
		if (informe == null) {
			throw new ResourceNotFoundException("Esse informe não existe");
		} else {
//			informesReleasesRepository.deleteById(id);
			informe.setAtivo(false);

			informe.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
			informe.setUsuario(usuarioRepository.getReferenceById(ususarioId));
			informesReleasesRepository.save(informe);
			usuarioRepository.save(usuarioRepository.getReferenceById(ususarioId));
		}
	}

	public List<RiosDTO> relatoriosRios() throws IOException {
		String municipio;
		String rio;
		String estacao;
		String status;
		String leitura;
		String monitoramento;
		List<RiosDTO> riosDTOs = new ArrayList<>();
		Elements linha = null;

		Document doc = Jsoup.connect("http://200.20.53.8/dados/piabanha.php").timeout(6000).get();

		Elements body = doc.select("table#Table tbody");
		Elements tabela = body.select("tr");
		System.out.println(tabela.size());

		for (int i = 0; i < tabela.size(); i++) {
			if (tabela.get(i).select("td").size() > 0) {
				if (tabela.get(i).select("td").get(0).text().contains("Petr")) {
					linha = tabela.get(i).select("td");

					municipio = linha.get(0).text();
					rio = linha.get(1).text();
					estacao = linha.get(2).text();
					status = linha.get(3).select("img").attr("src");

					if (!status.isEmpty()) {
						String[] imagem = status.split("/");
						status = imagem[2].substring(0, imagem[2].lastIndexOf('.'));
					}

					leitura = linha.get(4).text();
					monitoramento = linha.get(5).text();
					riosDTOs.add(new RiosDTO(municipio, rio, estacao, status, leitura, monitoramento));
				}
			}
		}
		return riosDTOs;
	}
}