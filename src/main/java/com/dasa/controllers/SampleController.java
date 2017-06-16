package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.domain.Participacao;
import com.dasa.service.DadosPopulacionaisService;
import com.dasa.utils.Util;
import javax.validation.Valid;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
            return service.getParticipacaoPorAno(ano);
	}
        
        @RequestMapping("/proporcao/{ano}")
	public String getProporcaoPorAno(@PathVariable int ano){	
            return service.getProporcaoPorAno(ano);
	}
        
        @RequestMapping(value="/notifica", method = RequestMethod.POST)
	public String notificaParticipacao(@RequestParam("sexo") String sexo, 
                @RequestParam("campanha") String campanha, @RequestParam("ano") int ano){
            
            sexo = sexo.toUpperCase();
            if(String.valueOf(sexo.charAt(0)).equals("M"))
                sexo = Util.masculino;
            if(String.valueOf(sexo.charAt(0)).equals("F"))
                sexo = Util.feminino;
            Participacao participacao = new Participacao(sexo, campanha, ano);
            service.getParticipacaoDadosList().add(participacao);
            return "Dados adicionados com sucesso";
	}		
}
