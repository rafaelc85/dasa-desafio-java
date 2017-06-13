package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.service.DadosPopulacionaisService;

@RestController
public class SampleController {

	@Autowired
	DadosPopulacionaisService service;
	
	@RequestMapping("/hello")
	public String helloWorld(){
		return "Hello =)";
	}
	
	@RequestMapping("/2010")
	public EstatisticaAnoResponse get2010data(){
		
		DadoPopulacional pop = service.obterPopulacaoPorAno(Optional.of("2010"));
		EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);
		
		return stat;
	}
	
}
