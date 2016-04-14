/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

/**
 *
 * @author Ludus
 */
public class MAIN
{
    public static void main(String[] args)
    {
        Fila fila = new Fila();
        Produtor p = new Produtor(1, fila, 5);
        Consumidor c = new Consumidor(1, fila, 2);
  
        p.start();
        c.start();
    }
}
