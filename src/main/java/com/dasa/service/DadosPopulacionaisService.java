package com.dasa.service;

import com.dasa.domain.DadoPopulacional;

import java.util.Optional;

import org.springframework.stereotype.Service;


public interface DadosPopulacionaisService {

    DadoPopulacional obterPopulacaoPorAno(final Optional<String>  ano);

}
