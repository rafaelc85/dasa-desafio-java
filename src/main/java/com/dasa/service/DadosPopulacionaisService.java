package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.Participacao;
import com.dasa.domain.ProporcaoParticipacao;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;



public interface DadosPopulacionaisService {

    DadoPopulacional obterPopulacaoPorAno(final Optional<String>  ano);
    double calculaR(final Optional<String>  anoInicial, final Optional<String>  anoFinal);
    DadoPopulacional get2017Data(DadoPopulacional pop, double r);
  
}
