package br.gov.api.DefesaCivil.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.api.DefesaCivil.entities.QuemSomosEntity;

@Repository
public interface QuemSomosRepository extends JpaRepository<QuemSomosEntity, Long> {
  
}