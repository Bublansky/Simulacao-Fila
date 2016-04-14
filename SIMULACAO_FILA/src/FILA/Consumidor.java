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
 * consumo de 10000 a 12000 ms
 * 
 */
public class Consumidor extends Thread
{
    private int idConsumidor;
    private Fila fila;
    private int totalConsumir;
    private Date data;
    private long tempoAnterior = 0;
    private Random rng;
    private int intervalo;
    private int rngRange = 2000;
    private int intervaloAdd = 5000;
 
    public Consumidor(int id, Fila p, int totalConsumir)
    {
        idConsumidor = id;
        fila = p;
        this.totalConsumir = totalConsumir;
        this.data = new Date();
        this.rng = new Random();
    }
    @Override
    public void run()
    {
        System.out.println("Consumidor iniciado!");
        tempoAnterior = new Date().getTime();
        intervalo = rng.nextInt(rngRange) + intervaloAdd;
        while(true)
        {
            //System.out.println("TA:" + tempoAnterior  + "/IV:" + intervalo + "/SOMA:" + (tempoAnterior + intervalo) + "/DA:" + new Date().getTime());
            if(new Date().getTime() >= tempoAnterior + intervalo)
            {
                if(fila.getSize() > 0)
                {
                    fila.get();
                    tempoAnterior = new Date().getTime();
                    intervalo = rng.nextInt(rngRange) + intervaloAdd;
                }
                
            }
        }
    }
}
