package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.domain.ParticipacaoDados;
import com.dasa.service.DadosPopulacionaisService;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SampleController implements ErrorController{

	@Autowired
	DadosPopulacionaisService service;
        
        private static final String PATH = "/error";

        @RequestMapping(value = PATH)
        public String error() {
            return "Erro inesperado";
        }

        @Override
        public String getErrorPath() {
            return PATH;
        }
	
	@RequestMapping(path = "/hello")        
	public String helloWorld(){
            return "Hello =)";
	}
	
	@RequestMapping("/2010")
	public EstatisticaAnoResponse get2010data(){	
            DadoPopulacional pop = service.obterPopulacaoPorAno(Optional.of("2010"));
            EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);           		
            return stat;                          
	}
        
        @RequestMapping("/2017")
	public EstatisticaAnoResponse get2017data(){	               
            double r = service.calculaR(Optional.of("2000"),Optional.of("2016"));                             
            DadoPopulacional pop = service.obterPopulacaoPorAno(Optional.of("2016"));                            
            pop = service.get2017Data(pop, r);
            EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);                               
            return stat;                          
	}
	
        @RequestMapping("/participacao/{ano}")
	public String getParticipacaoPorAno(@PathVariable int ano){	
            ParticipacaoDados pd = new ParticipacaoDados();
            return pd.getParticipacaoPorAno(ano);
	}
        
        @RequestMapping("/proporcao/{ano}")
	public String getProporcaoPorAno(@PathVariable int ano){	
            ParticipacaoDados pd = new ParticipacaoDados();
            return pd.getProporcaoPorAno(ano);
	}
}
