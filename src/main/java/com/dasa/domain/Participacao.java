/*
 */

package com.dasa.domain;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
public class Participacao {      
    private String sexo;
    private String campanha;
    int ano;

    public Participacao(String sexo, String campanha, int ano) {
        this.sexo = sexo;
        this.campanha = campanha;
        this.ano = ano;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Participacao{" + "sexo=" + sexo + ", campanha=" + campanha + ", ano=" + ano + '}';
    }
    
    
}
