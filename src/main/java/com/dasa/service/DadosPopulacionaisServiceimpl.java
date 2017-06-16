package com.dasa.service;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.Participacao;
import com.dasa.domain.ParticipacaoDados;
import com.dasa.domain.ProporcaoParticipacao;
import com.dasa.repository.DadosPopulacionaisRepository;
import com.dasa.utils.Util;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DadosPopulacionaisServiceimpl implements DadosPopulacionaisService {

    @Autowired
    private DadosPopulacionaisRepository dadosPopulacionaisRepository;
    
    private final ParticipacaoDados pd = new ParticipacaoDados();

    @Override
    public DadoPopulacional obterPopulacaoPorAno(final Optional<String> ano) {

        final String anoCenso = ano.get();

        if (!ano.isPresent()) {
            throw new IllegalArgumentException("Parametro Ano é Obrigatório");
        }
        
        return dadosPopulacionaisRepository.findByAno(anoCenso);
    }

    @Override
    public double calculaR(Optional<String> anoInicial, Optional<String> anoFinal) {
        DadoPopulacional popInicial = obterPopulacaoPorAno(anoInicial);
        DadoPopulacional popFinal = obterPopulacaoPorAno(anoFinal);
        
        BigDecimal numBig; 
        numBig = popFinal.getPopulacaoTotal().divide(popInicial.getPopulacaoTotal(), 2, BigDecimal.ROUND_HALF_UP);
        
        double dif = Double.parseDouble(popFinal.getAno()) - Double.parseDouble(popInicial.getAno());        
        double r = Util.root(numBig.doubleValue(), dif); 
     
        return r-1;
    }

    @Override
    public DadoPopulacional get2017Data(DadoPopulacional pop, double r) {
            r++;     
            BigDecimal populacaoTotal = pop.getPopulacaoTotal().multiply(new BigDecimal(r), 
                    MathContext.UNLIMITED);
            populacaoTotal = populacaoTotal.setScale(0, RoundingMode.HALF_UP);

            /*  Aplicando a seguinte formula para cálculo da populacao masculina:            
            *   numeroDeHomens = numero de homens no ano anterior * r * k 
            *   onde k é o fator de redução anual médio da população masculina
            *   e r é a taxa de crescimento da população brasileira (conforme enunciado)
            */  
            BigDecimal totalHomens = new BigDecimal(pop.getTotalHomens());
            totalHomens = totalHomens.multiply(
                    new BigDecimal("0.9996"), MathContext.UNLIMITED);
            totalHomens = totalHomens.multiply(
                    new BigDecimal(r), MathContext.UNLIMITED);
            totalHomens = totalHomens.setScale(0, RoundingMode.HALF_UP);
            
            //a populacao é feminina é a populacao total menos o número de homens
            BigDecimal totalMulheres = populacaoTotal.subtract(totalHomens);
            
            return new DadoPopulacional("2017", populacaoTotal.toPlainString(), 
                        totalHomens.toPlainString(), totalMulheres.toPlainString());            
            
    }

    @Override
    public ArrayList<Participacao> getParticipacaoDadosList() {
        return this.pd.getParticipacaoList();
    }
    
    //monta uma lista com todas as participacoes para do ano fornecido
    @Override
    public ArrayList<Participacao> filtraPorAno(int ano){
        ArrayList<Participacao> filterList = new ArrayList<>();
        if(pd.getParticipacaoList().isEmpty()) return null;
        for (int i = 0; i < pd.getParticipacaoList().size(); i++) {
            if(pd.getParticipacaoList().get(i).getAno() == ano)
                filterList.add(pd.getParticipacaoList().get(i));
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
