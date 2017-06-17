package com.dasa.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.dasa.domain.Participacao;
import com.dasa.service.ParticipacaoService;
import java.util.Map;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class ParticipacaoController {
       
        @Autowired
	ParticipacaoService participacaoService;
	
	@Autowired
	MessageSource messageSource;
        
        @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(ModelMap model) {
            return "home";
        }
        
        @RequestMapping(value = "/newParticipacao", method = RequestMethod.GET)
	public String newParticipacao(Map<String, Object> model) {
                model.put("participacao", new Participacao());
		return "newParticipacao";
	}
        
        @RequestMapping(value = "/newParticipacao", method = RequestMethod.POST)
	public String saveParticipacao(@Valid Participacao participacao, BindingResult result,
			ModelMap model) {
            
		if (result.hasErrors()) {
			return "newParticipacao";
		}	
		participacaoService.saveParticipacao(participacao);
		model.addAttribute("mensagens", "Participação cadastrada com sucesso");
		return "mensagens";
	}
        
 
}
