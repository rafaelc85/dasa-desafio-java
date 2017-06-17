package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.Participacao;
import com.dasa.domain.ProporcaoParticipacao;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;



public interface ParticipacaoService {    
    Participacao findById(int id);	
    void saveParticipacao(Participacao participacao);	
    void updateParticipacao(Participacao participacao);	
    void deleteParticipacaoById(int id);
    List<Participacao> findAllParticipacoes(); 
    
    ArrayList<Participacao> filtraPorAno(int ano);
    String getParticipacaoPorAno(int ano);
    String getProporcaoPorAno(int ano);
    ArrayList<ProporcaoParticipacao> createArrayProporcao(ArrayList<Participacao> filterList);
}
