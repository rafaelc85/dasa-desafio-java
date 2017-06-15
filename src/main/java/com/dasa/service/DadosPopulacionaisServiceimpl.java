package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.utils.Util;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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

    @Override
    public DadoPopulacional get2017Data(DadoPopulacional pop, double r) {
            r++;     
            BigDecimal populacaoTotal = pop.getPopulacaoTotal().multiply(new BigDecimal(r), 
                    MathContext.UNLIMITED);
            populacaoTotal = populacaoTotal.setScale(0, RoundingMode.HALF_UP);

             //Aplicando fator de redução anual médio da população masculina
            BigDecimal totalHomens = populacaoTotal.multiply(
                    new BigDecimal("0.4934"), MathContext.UNLIMITED);
            totalHomens = totalHomens.setScale(0, RoundingMode.HALF_UP);
            BigDecimal totalMulheres = populacaoTotal.subtract(totalHomens);
            
            return new DadoPopulacional("2017", populacaoTotal.toPlainString(), 
                        totalHomens.toPlainString(), totalMulheres.toPlainString());
    }
}
