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
        Fila fila = new Fila();
        Produtor p = new Produtor(1, fila, 5, false);
        Consumidor c = new Consumidor(1, fila, 2, false);
        p.start();
        c.start();
    }
}
