/*
 */

package com.dasa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
@Entity
@Table(name="participacao")
public class Participacao {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(min=3, max=15)
    @Column(name = "sexo", nullable = false)
    private String sexo;
    
    @Size(min=3, max=50)
    @Column(name = "campanha", nullable = false)
    private String campanha;
    
    @Min(4) 
    @Column(name = "ano", nullable = false)
    int ano;
    
    public Participacao(){}

    public Participacao(String sexo, String campanha, int ano) {
        this.sexo = sexo;
        this.campanha = campanha;
        this.ano = ano;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
