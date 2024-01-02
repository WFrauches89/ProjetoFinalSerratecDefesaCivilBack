package br.gov.api.DefesaCivil.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.api.DefesaCivil.common.ConversorData;
import br.gov.api.DefesaCivil.dto.QuemSomos.QuemSomosRequestDTO;
import br.gov.api.DefesaCivil.dto.QuemSomos.QuemSomosResponseDTO;
import br.gov.api.DefesaCivil.entities.QuemSomosEntity;
import br.gov.api.DefesaCivil.entities.UsuarioEntity;
import br.gov.api.DefesaCivil.repositories.QuemSomosRepository;
import br.gov.api.DefesaCivil.repositories.UsuarioRepository;

@Service
public class QuemSomosService {
	@Autowired
	private final QuemSomosRepository quemSomosRepository;

	@Autowired
	private final ModelMapper modelMapper;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;

	public QuemSomosService(QuemSomosRepository quemSomosRepository, ModelMapper modelMapper) {
		this.quemSomosRepository = quemSomosRepository;
		this.modelMapper = modelMapper;
	}

	public List<QuemSomosResponseDTO> listarTodosQuemSomos() {
		List<QuemSomosEntity> quemSomosList = quemSomosRepository.findAll();

		return quemSomosList.stream()
				.map(quemSomosEntity -> modelMapper.map(quemSomosEntity, QuemSomosResponseDTO.class))
				.collect(Collectors.toList());
	}

	public QuemSomosResponseDTO obterQuemSomosPorId(Long id) {
		QuemSomosEntity quemSomosEntity = encontrarPorId(id);
		return modelMapper.map(quemSomosEntity, QuemSomosResponseDTO.class);
	}

	public QuemSomosResponseDTO cadastrarQuemSomos(QuemSomosRequestDTO requestDTO) {
		QuemSomosEntity quemSomosEntity = modelMapper.map(requestDTO, QuemSomosEntity.class);
		quemSomosEntity.setId(null);
		quemSomosEntity.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		quemSomosEntity.setUsuario(modelMapper.map(usuarioService.obterPorId(requestDTO.getUsuarioId()), UsuarioEntity.class));
		QuemSomosEntity entidadeSalva = quemSomosRepository.save(quemSomosEntity);

		return modelMapper.map(entidadeSalva, QuemSomosResponseDTO.class);
	}

	public QuemSomosResponseDTO editarQuemSomos(Long id, QuemSomosRequestDTO requestDTO) {
		encontrarPorId(id);
		QuemSomosEntity novoQuem = modelMapper.map(requestDTO, QuemSomosEntity.class);
		novoQuem.setDataEdicao(ConversorData.converterDateParaDataHora(new Date()));
		novoQuem.setId(id);
		novoQuem.setUsuario(modelMapper.map(usuarioService.obterPorId(requestDTO.getUsuarioId()), UsuarioEntity.class));

		return modelMapper.map(quemSomosRepository.save(novoQuem), QuemSomosResponseDTO.class);
	}

	private QuemSomosEntity encontrarPorId(Long id) {
		return quemSomosRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("QuemSomosEntity não encontrada com o ID: " + id));
	}
}