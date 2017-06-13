package com.dasa.repository;

import com.dasa.domain.DadoPopulacional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;

@Transactional
public interface DadosPopulacionaisRepository extends CrudRepository<DadoPopulacional, Long> {

    DadoPopulacional findByAno(final String ano);


}
