package br.gov.api.DefesaCivil.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.api.DefesaCivil.common.ConversorData;
import br.gov.api.DefesaCivil.dto.PontoDeApoio.PontoDeApoioRequestDTO;
import br.gov.api.DefesaCivil.dto.PontoDeApoio.PontoDeApoioResponseDTO;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioRequestDTO;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioResponseDTO;
import br.gov.api.DefesaCivil.entities.PontoDeApoioEntity;
import br.gov.api.DefesaCivil.entities.TelefonePontoDeApoioEntity;
import br.gov.api.DefesaCivil.entities.UsuarioEntity;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceNotFoundException;
import br.gov.api.DefesaCivil.repositories.PontoDeApoioRepository;
import br.gov.api.DefesaCivil.repositories.UsuarioRepository;

@Service
public class PontoDeApoioService {

	@Autowired
	private PontoDeApoioRepository pontosDeApoioRepository;

	@Autowired
	private TelefonePontoDeApoioService telefonePontoDeApoioService;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	public List<PontoDeApoioResponseDTO> listarTodosPontosDeApoio() {
		List<PontoDeApoioEntity> pontosDeApoio = pontosDeApoioRepository.findByOcultarFalseOrderByIdDesc();

		return pontosDeApoio.stream().map(pontoDeApoio -> mapper.map(pontoDeApoio, PontoDeApoioResponseDTO.class))
				.collect(Collectors.toList());
	}

	public PontoDeApoioResponseDTO obterPontoDeApoioPorId(Long id) {
		Optional<PontoDeApoioEntity> optPontoDeApoio = pontosDeApoioRepository.findById(id);

		if (optPontoDeApoio.isEmpty()) {
			throw new ResourceNotFoundException("Nenhum registro encontrado para o ID: " + id);
		}

		return mapper.map(optPontoDeApoio.get(), PontoDeApoioResponseDTO.class);
	}

	@Transactional
	public PontoDeApoioResponseDTO cadastrarPontosDeApoio(PontoDeApoioRequestDTO pontosDeApoioRequest) {
		pontosDeApoioRequest = criarRotaMaps(pontosDeApoioRequest);
		pontosDeApoioRequest.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		PontoDeApoioEntity novoPonto = mapper.map(pontosDeApoioRequest, PontoDeApoioEntity.class);
		novoPonto.setId(null);
		novoPonto = pontosDeApoioRepository.save(novoPonto);
		List<TelefonePontoDeApoioEntity> telefonesAdicionados = adicionarNumeros(
				pontosDeApoioRequest.getNumerosTelefone(), novoPonto);
		novoPonto.setNumerosTelefone(telefonesAdicionados);
		novoPonto.setUsuario(mapper.map(usuarioService.obterPorId(pontosDeApoioRequest.getUsuarioId()) , UsuarioEntity.class));
		pontosDeApoioRepository.save(novoPonto);
		return mapper.map(novoPonto, PontoDeApoioResponseDTO.class);
	}

	@Transactional
	public PontoDeApoioResponseDTO editarPontoDeApoio(Long id, PontoDeApoioRequestDTO pontosDeApoioRequest) {
		var teste = obterPontoDeApoioPorId(id);
		pontosDeApoioRequest = criarRotaMaps(pontosDeApoioRequest);
		PontoDeApoioEntity novoPonto = mapper.map(pontosDeApoioRequest, PontoDeApoioEntity.class);
		novoPonto.setId(id);

		telefonePontoDeApoioService.deletarTodosNumeros(mapper.map(teste, PontoDeApoioEntity.class));
		List<TelefonePontoDeApoioEntity> telefonesAdicionados = adicionarNumeros(
				pontosDeApoioRequest.getNumerosTelefone(), novoPonto);
		novoPonto.setNumerosTelefone(telefonesAdicionados);
		novoPonto.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		novoPonto.setUsuario(mapper.map(usuarioService.obterPorId(pontosDeApoioRequest.getUsuarioId()) , UsuarioEntity.class));
		pontosDeApoioRepository.save(novoPonto);

		return mapper.map((novoPonto), PontoDeApoioResponseDTO.class);
	}

	public PontoDeApoioRequestDTO criarRotaMaps(PontoDeApoioRequestDTO pontosDeApoioRequest) {
		pontosDeApoioRequest.setLocalizacao(
				"https://www.google.com.br/maps/search/" + pontosDeApoioRequest.getPonto_apoio().replace(" ", "+") + "+"
						+ pontosDeApoioRequest.getEndereco().replace(" ", "+") + "+"
						+ pontosDeApoioRequest.getBairro().replace(" ", "+") + "+Petropolis,+RJ");
		return pontosDeApoioRequest;
	}

	private List<TelefonePontoDeApoioEntity> adicionarNumeros(
		List<TelefonePontoDeApoioRequestDTO> telefonePontoDeApoioRequest, PontoDeApoioEntity novoPonto) {
		List<TelefonePontoDeApoioEntity> telefonesAdicionados = new ArrayList<>();

		for (TelefonePontoDeApoioRequestDTO telRequest : telefonePontoDeApoioRequest) {
			TelefonePontoDeApoioEntity telefoneModel = mapper.map(telRequest, TelefonePontoDeApoioEntity.class);
			telefoneModel.setPontoDeApoio(novoPonto);
			TelefonePontoDeApoioResponseDTO TelefoneResponse = telefonePontoDeApoioService.adicionar(telefoneModel);
			telefonesAdicionados.add(mapper.map(TelefoneResponse, TelefonePontoDeApoioEntity.class));
		}
		
		return telefonesAdicionados;
	}

	public PontoDeApoioResponseDTO alterarStatus(Long id, Long idUsuario) {
		var PontoDeApoio = obterPontoDeApoioPorId(id);
		PontoDeApoio.setStatus(PontoDeApoio.getStatus() == false ? true : false);
		PontoDeApoio.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		PontoDeApoioEntity salva = mapper.map(PontoDeApoio, PontoDeApoioEntity.class);
		salva.setUsuario(mapper.map(usuarioService.obterPorId(idUsuario) , UsuarioEntity.class));
		pontosDeApoioRepository.save(salva);
		
		return mapper.map(salva, PontoDeApoioResponseDTO.class);
	}

	public PontoDeApoioResponseDTO removerPontosDeApoio(Long id, Long idUsuario) {
		var PontoDeApoio = obterPontoDeApoioPorId(id);
		PontoDeApoio.setOcultar(true);
		PontoDeApoio.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		PontoDeApoioEntity salva = mapper.map(PontoDeApoio, PontoDeApoioEntity.class);
		salva.setUsuario(mapper.map(usuarioService.obterPorId(idUsuario) , UsuarioEntity.class));
		salva = pontosDeApoioRepository.save(salva);

		return mapper.map(salva, PontoDeApoioResponseDTO.class);
	}
}
