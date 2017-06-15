/*
 */

package com.dasa.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Rafael Coutinho <rafaelc85@gmail.com>
 */
public class Util{
    public static final String masculino = "MASCULINO";
    public static final String feminino  = "FEMININO";
    
    public static double root(double num, double root) {
        return Math.pow(Math.E, Math.log(num)/root);
    } 
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
