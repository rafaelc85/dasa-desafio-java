/*
 */

package com.dasa.domain;

import com.dasa.utils.Util;
import java.util.ArrayList;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
public class ParticipacaoDados {
    private ArrayList<Participacao> participacaoList;
    
    public ParticipacaoDados(){
        participacaoList = new ArrayList<>();
        this.loadData();
    }    

    public ArrayList<Participacao> getParticipacaoList() {
        return participacaoList;
    }

    public void setParticipacaoList(ArrayList<Participacao> participacaoList) {
        this.participacaoList = participacaoList;             
    }   

    private void loadData() {
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-1", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2010-1", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-1", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-1", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2010-2", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-3", 2010));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2010-3", 2010));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2011-1", 2011));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2011-1", 2011));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2011-2", 2011));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2012-1", 2012));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2013-2", 2013));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2014-1", 2014));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2015-1", 2015));
        this.participacaoList.add(new Participacao(Util.masculino, "Campanha2016-1", 2016));
        this.participacaoList.add(new Participacao(Util.feminino, "Campanha2016-2", 2016));
    }
    
}
