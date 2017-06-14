/*
 */

package com.dasa.domain;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
public class ProporcaoParticipacao {
    String Campanha;
    int homens, mulheres;
    
    public ProporcaoParticipacao(String Campanha) {
        this.Campanha = Campanha;
        this.homens = 0;
        this.mulheres = 0;
    }

    public ProporcaoParticipacao(String Campanha, int homens, int mulheres) {
        this.Campanha = Campanha;
        this.homens = homens;
        this.mulheres = mulheres;
    }
    
    public void addHomem(){
        this.homens++;
    }
    
    public void addMulher(){
        this.mulheres++;
    }

    public String getCampanha() {
        return Campanha;
    }

    public void setCampanha(String Campanha) {
        this.Campanha = Campanha;
    }

    public int getHomens() {
        return homens;
    }

    public void setHomens(int homens) {
        this.homens = homens;
    }

    public int getMulheres() {
        return mulheres;
    }

    public void setMulheres(int mulheres) {
        this.mulheres = mulheres;
    }

    @Override
    public String toString() {
        return "ProporcaoParticipacao{" + "Campanha=" + Campanha + ", homens=" + homens + ", mulheres=" + mulheres + '}';
    }
    
    
       
}
