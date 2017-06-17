package com.dasa.service;

import com.dasa.dao.ParticipacaoDao;
import com.dasa.domain.Participacao;
import com.dasa.domain.ProporcaoParticipacao;
import com.dasa.utils.Util;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service("partipacaoService")
@Transactional
public class ParticipacaoServiceimpl implements ParticipacaoService {

    @Autowired
    private ParticipacaoDao dao;

    @Override
    public Participacao findById(int id) {
            return dao.findById(id);
    }

    @Override
    public void saveParticipacao(Participacao participacao) {
            dao.saveParticipacao(participacao);
    }

    @Override
    public void updateParticipacao(Participacao participacao) {
            Participacao entity = dao.findById(participacao.getId());
            if(entity!=null){
                    entity.setSexo(participacao.getSexo());
                    entity.setCampanha(participacao.getCampanha());
                    entity.setAno(participacao.getAno());
            }
    }

    @Override
    public void deleteParticipacaoById(int id) {
            dao.deleteParticipacaoById(id);
    }

    @Override
    public List<Participacao> findAllParticipacoes() {
            return dao.findAllParticipacoes();
    }        
    
    //monta uma lista com todas as participacoes para do ano fornecido
    @Override
    public ArrayList<Participacao> filtraPorAno(int ano){
        List<Participacao> pd = findAllParticipacoes();
        ArrayList<Participacao> filterList = new ArrayList<>();
        if(pd.isEmpty()) return null;
        for (int i = 0; i < pd.size(); i++) {
            if(pd.get(i).getAno() == ano)
                filterList.add(pd.get(i));
        }
        return filterList;
    }    
    
    //obtem os dados de participação por Campanha, realizando o filtro por Ano.
    @Override
    public String getParticipacaoPorAno(int ano){
        ArrayList<Participacao> filterList = filtraPorAno(ano);
        if(filterList.isEmpty()) return "Nenhum dado para o ano solicitado";
        String str = "";
        for (int i = 0; i < filterList.size(); i++) {            
            str += filterList.get(i).toString() + "\n";
        }
        return str;
    }
    
    //obtem a proporção de participação entre Homens X Mulheres X Campanha filtrando pelo ano.
    @Override
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
    @Override
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
	
}
