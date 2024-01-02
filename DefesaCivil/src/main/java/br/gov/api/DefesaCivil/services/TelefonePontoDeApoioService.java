package br.gov.api.DefesaCivil.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioRequestDTO;
import br.gov.api.DefesaCivil.dto.TelefonePontoDeApoio.TelefonePontoDeApoioResponseDTO;
import br.gov.api.DefesaCivil.entities.PontoDeApoioEntity;
import br.gov.api.DefesaCivil.entities.TelefonePontoDeApoioEntity;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceNotFoundException;
import br.gov.api.DefesaCivil.repositories.TelefonePontoDeApoioRepository;

@Service
public class TelefonePontoDeApoioService {

	@Autowired
	private TelefonePontoDeApoioRepository telefonePontoDeApoioRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public TelefonePontoDeApoioResponseDTO obterPorId(Long id) {
		Optional<TelefonePontoDeApoioEntity> optTel = telefonePontoDeApoioRepository.findById(id);
		
		if(optTel.isEmpty()){
            throw new ResourceNotFoundException("Nenhum registro encontrado para o ID: " + id);
        }
		
		return mapper.map(optTel.get(), TelefonePontoDeApoioResponseDTO.class);
	}
	
	public TelefonePontoDeApoioResponseDTO adicionar(TelefonePontoDeApoioEntity telefonePontoDeApoio){
		return mapper.map(telefonePontoDeApoioRepository.save(telefonePontoDeApoio), TelefonePontoDeApoioResponseDTO.class);
	}
	
	public void deletar(Long id) {
		obterPorId(id);
		telefonePontoDeApoioRepository.deleteById(id);
	}
	
	public void deletarTodosNumeros(PontoDeApoioEntity pontoDeApoio){
		List<TelefonePontoDeApoioEntity> numerosExcluir = telefonePontoDeApoioRepository.findByPontoDeApoio(pontoDeApoio);
		for(TelefonePontoDeApoioEntity pdI : numerosExcluir){
			deletar(pdI.getId());
		}
	}
}
