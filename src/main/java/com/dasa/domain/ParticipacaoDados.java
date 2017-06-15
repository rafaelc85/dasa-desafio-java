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
    
    public ArrayList<Participacao> filtraPorAno(int ano){
        ArrayList<Participacao> filterList = new ArrayList<>();
        if(participacaoList.isEmpty()) return null;
        for (int i = 0; i < participacaoList.size(); i++) {
            if(participacaoList.get(i).getAno() == ano)
                filterList.add(participacaoList.get(i));
        }
        return filterList;
    }    
    
    //obtem os dados de participação por Campanha, realizando o filtro por Ano.
    public String getParticipacaoPorAno(int ano){
        ArrayList<Participacao> filterList = filtraPorAno(ano);
        if(filterList.isEmpty()) return "Nenhum dado para o ano solicitado";
        String str = "";
        for (int i = 0; i < filterList.size(); i++) {            
            str += participacaoList.get(i).toString() + "\n";
        }
        return str;
    }
    
    //obtem a proporção de participação entre Homens X Mulheres X Campanha filtrando pelo ano.
    public String getProporcaoPorAno(int ano){
        ArrayList<Participacao> filterList = filtraPorAno(ano);
        if(filterList.isEmpty()) return "Nenhum dado para o ano solicitado";
        ArrayList<ProporcaoParticipacao> proporcaoList = createArrayProporcao(filterList);
        if(proporcaoList.isEmpty()) return "Nenhuma campanha para ser exibida";
        String str = "";
        for (int i = 0; i < proporcaoList.size(); i++) { 
            double numParticipacoes, porcHomens, porcMulheres;
            numParticipacoes = proporcaoList.get(i).getHomens() + proporcaoList.get(i).getMulheres();
            porcHomens = proporcaoList.get(i).getHomens() / numParticipacoes * 100;
            porcMulheres = proporcaoList.get(i).getMulheres() / numParticipacoes * 100;
            str += "{Campanha: " + proporcaoList.get(i).getCampanha() +   
                    "; Total de participacoes: " + Util.round(numParticipacoes,0) + 
                    "; Total de homens: " + proporcaoList.get(i).getHomens() + 
                    "; Porcentagem de homens: " + Util.round(porcHomens,2) + "%" + 
                    "; Total de mulheres: " + proporcaoList.get(i).getMulheres() + 
                    "; Porcentagem de mulheres: " + Util.round(porcMulheres,2) + "%} \n"; 
        }
        return str;  
    }
    
    //cria um array com a quantidade de participacoes de homens e mulheres por campanha
    public ArrayList<ProporcaoParticipacao> createArrayProporcao(ArrayList<Participacao> filterList){
        ArrayList<ProporcaoParticipacao> proporcaoList = new ArrayList<>(); 
        boolean add = true;
        for (int i = 0; i < filterList.size(); i++) { 
            for (int j = 0; j < proporcaoList.size(); j++) { 
                if(proporcaoList.get(j).getCampanha().equals(filterList.get(i).getCampanha())){
                    if(filterList.get(i).getSexo().equals(Util.masculino)){
                        proporcaoList.get(j).addHomem();
                        add = false;
                    }
                    if(filterList.get(i).getSexo().equals(Util.feminino)){
                        proporcaoList.get(j).addMulher();
                        add = false;
                    }
                }
            }
            if(add){ 
                ProporcaoParticipacao pp = new ProporcaoParticipacao(filterList.get(i).getCampanha());
                if(filterList.get(i).getSexo().equals(Util.masculino))
                    pp.addHomem();
                else
                    pp.addMulher();
                proporcaoList.add(pp);                    
            }else
                add = true;
        }
        return proporcaoList;
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
