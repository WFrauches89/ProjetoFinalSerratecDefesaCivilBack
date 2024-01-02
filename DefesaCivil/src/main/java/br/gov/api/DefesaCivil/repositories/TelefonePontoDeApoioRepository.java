package br.gov.api.DefesaCivil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.api.DefesaCivil.entities.PontoDeApoioEntity;
import br.gov.api.DefesaCivil.entities.TelefonePontoDeApoioEntity;

import java.util.List;

public interface TelefonePontoDeApoioRepository extends JpaRepository <TelefonePontoDeApoioEntity, Long>{
	
	List<TelefonePontoDeApoioEntity> findByPontoDeApoio(PontoDeApoioEntity pontoDeApoio);
}
