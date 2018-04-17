/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pruebas.util;

import edu.pruebas.model.Carro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ismael
 */
public class DatosService {
    private static List<Carro> carros;
    
    public static List<Carro> getCarros(){
        if(carros == null){
            carros = new ArrayList<>();
            for (int i = 0; i < 131; i++) {
                carros.add(new Carro(i, "Modelo " + (1910+i), new Date(10+i, 3, 21)));
            }
        }
        return carros;
    }
    
    public static List<Carro> getCarros(int start, int cant){
        int t = start + cant;
        return getCarros().subList(start, (getCarros().size()<= t)?getCarros().size():t);
    }
}
