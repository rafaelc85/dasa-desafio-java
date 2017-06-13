package com.dasa.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class EstatisticaAnoResponse {
	private final String ano;
	
	public EstatisticaAnoResponse(DadoPopulacional pop) {
		this.ano = pop.getAno();
	}
}
