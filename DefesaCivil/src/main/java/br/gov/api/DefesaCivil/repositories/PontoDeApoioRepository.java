package br.gov.api.DefesaCivil.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.gov.api.DefesaCivil.entities.PontoDeApoioEntity;
import java.util.List;

public interface PontoDeApoioRepository extends JpaRepository <PontoDeApoioEntity, Long>{
	
    @Query
	List<PontoDeApoioEntity> findByOcultarFalseOrderByIdDesc();
}
