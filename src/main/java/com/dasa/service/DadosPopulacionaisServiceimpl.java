package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.util.Util;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DadosPopulacionaisServiceimpl implements DadosPopulacionaisService {

    @Autowired
    private DadosPopulacionaisRepository dadosPopulacionaisRepository;

    @Override
    public DadoPopulacional obterPopulacaoPorAno(final Optional<String> ano) {

        final String anoCenso = ano.get();

        if (!ano.isPresent()) {
            throw new IllegalArgumentException("Parametro Ano é Obrigatório");
        }
        
        return dadosPopulacionaisRepository.findByAno(anoCenso);
    }

    @Override
    public double calculaR(Optional<String> anoInicial, Optional<String> anoFinal) {
        DadoPopulacional popInicial = obterPopulacaoPorAno(anoInicial);
        DadoPopulacional popFinal = obterPopulacaoPorAno(anoFinal);
        
        BigDecimal numBig; 
        numBig = popFinal.getPopulacaoTotal().divide(popInicial.getPopulacaoTotal(), 2, BigDecimal.ROUND_HALF_UP);
        
        double dif = Double.parseDouble(popFinal.getAno()) - Double.parseDouble(popInicial.getAno());        
        double r = Util.root(numBig.doubleValue(), dif); 
     
        return r-1;
    }
}
