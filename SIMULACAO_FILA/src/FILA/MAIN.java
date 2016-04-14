/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Ludus
 */
public class MAIN
{
    
    
    public static void main(String[] args)
    {
        boolean RandomValues;
        Fila fila = new Fila();
        
            //System.out.println("Iniciado com valores totalmente aleatórios");
            RandomValues = false;
            System.out.println("Iniciado com entrada Poisson e saída exponencial");
        
        Produtor p = new Produtor(1, fila, 5, RandomValues);
        Consumidor c = new Consumidor(1, fila, 2, RandomValues);
        p.start();
        c.start();
    }
}
