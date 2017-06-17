package com.dasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.EstatisticaAnoResponse;
import com.dasa.service.DadosPopulacionaisService;
import com.dasa.service.ParticipacaoService;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController implements ErrorController{

	@Autowired
	DadosPopulacionaisService serviceDadosPopulacionais;
        
        @Autowired
	ParticipacaoService serviceParticipacao;
        
        private static final String PATH = "/error";

        @RequestMapping(value = PATH)
        public String error() {
            return "Erro inesperado";
        }

        @Override
        public String getErrorPath() {
            return PATH;
        }
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
        public ModelAndView hello() {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("hello");
            return mav;
        }        
	
	@RequestMapping("/2010")
	public EstatisticaAnoResponse get2010data(ModelMap model){	
            DadoPopulacional pop = serviceDadosPopulacionais.obterPopulacaoPorAno(Optional.of("2010"));
            EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);
            return stat;                                     
	}
        
        @RequestMapping("/2017")
	public EstatisticaAnoResponse get2017data(ModelMap model){	               
            double r = serviceDadosPopulacionais.calculaR(Optional.of("2000"),Optional.of("2016"));                             
            DadoPopulacional pop = serviceDadosPopulacionais.obterPopulacaoPorAno(Optional.of("2016"));                            
            pop = serviceDadosPopulacionais.get2017Data(pop, r);
            EstatisticaAnoResponse stat = new EstatisticaAnoResponse(pop);          
            return stat;                             
	}
	
        @RequestMapping("/participacao/{ano}")
	public String getParticipacaoPorAno(@PathVariable int ano, ModelMap model){             
            return serviceParticipacao.getParticipacaoPorAno(ano); 
	}
        
        @RequestMapping("/proporcao/{ano}")
	public String getProporcaoPorAno(@PathVariable int ano){	
            return serviceParticipacao.getProporcaoPorAno(ano);
	}

}
