package com.dasa.dao;

import com.dasa.domain.Participacao;
import java.util.List;

public interface ParticipacaoDao {

	Participacao findById(int id);

	void saveParticipacao(Participacao participacao);
	
	void deleteParticipacaoById(int id);
	
	List<Participacao> findAllParticipacoes();

}
