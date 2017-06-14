package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import java.math.BigDecimal;

import java.util.Optional;



public interface DadosPopulacionaisService {

    DadoPopulacional obterPopulacaoPorAno(final Optional<String>  ano);
    double calculaR(final Optional<String>  anoInicial, final Optional<String>  anoFinal);

}
