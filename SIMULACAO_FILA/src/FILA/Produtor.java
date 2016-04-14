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
 * produção de 900 a 1200 ms
 */
public class Produtor extends Thread
{
    private int idProdutor;
    private Fila fila;
    private int producaoTotal;
    private Date data;
    private int contador = 0;
    private long tempoAnterior = 0;
    private Random rng;
    private int intervalo;
    private int rngRange = 20;
    private int intervaloAdd = 3000;
    
    public Produtor(int id, Fila p, int producaoTotal)
    {
        idProdutor = id;
        fila = p;
        this.producaoTotal = producaoTotal;
        this.data = new Date();
        this.rng = new Random();
    }
    @Override
    public void run()
    { 
        System.out.println("Produtor iniciado!");
        tempoAnterior = new Date().getTime();
        intervalo = rng.nextInt(rngRange) + intervaloAdd;
        while(true)
        {
            if(new Date().getTime() >= tempoAnterior + intervalo)
            {
                fila.set(++contador);
                tempoAnterior = new Date().getTime();
                intervalo = rng.nextInt(rngRange) + intervaloAdd;
            }
        }
    }
}
