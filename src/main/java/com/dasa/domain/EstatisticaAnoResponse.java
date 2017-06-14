package com.dasa.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Data
public class EstatisticaAnoResponse {
	private final String ano;
        private BigDecimal populacaoTotal;
        private String porcentagemHomens;
        private String porcentagemMulheres;        
	
	public EstatisticaAnoResponse(DadoPopulacional pop) {
            this.ano = pop.getAno();
            this.populacaoTotal = pop.getPopulacaoTotal();
            this.porcentagemHomens = determinaPorcentagem(pop.getTotalHomens());
            this.porcentagemMulheres = determinaPorcentagem(pop.getTotalMulheres());
	}

        private String determinaPorcentagem(Long totalDoSexo) {
            if(this.populacaoTotal==null || this.populacaoTotal.equals(0)) return "";

            BigDecimal t = new BigDecimal(totalDoSexo);                    
            BigDecimal result = t.multiply(new BigDecimal("100"));
            result = result.divide(this.populacaoTotal, 2, BigDecimal.ROUND_HALF_UP);
                                  
            return result + "%";
        }
}
