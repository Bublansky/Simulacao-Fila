/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
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
    private int intervalo = 1000; // 1 segundo
    private int rngRange = 2000;
    private int intervaloAdd = 5000;
    private double timePassed = 0;
    private double y = 0.526;
    private double rngResult;
    private boolean isFullRandom = false;
 
    public Consumidor(int id, Fila p, int totalConsumir, boolean fullRandom)
    {
        idConsumidor = id;
        fila = p;
        this.totalConsumir = totalConsumir;
        this.data = new Date();
        this.rng = new Random();
        isFullRandom = fullRandom;
    }
    /*
        3.2 clientes por segundo entram na fila
        y = 3.2
        0.526 clientes por segundo são atendidos
        y = 0.526
    */
    @Override
    @SuppressWarnings("empty-statement")
    public void run()
    {
        try {
            System.out.println("Consumidor iniciado!");
            long lastTimeArrival, lastTimeExit, now;
            int multiplier = 1;
            int maxArrival = 5;
            int rngArrival;
            double ySaida = 0.526;
            int indice = 1;
            now = new Date().getTime();
            tempoAnterior = now;
            OutputStream file;
            file = new FileOutputStream("grafic.png");
            JFreeChart grafic;
            while(true)
            {
                now = new Date().getTime();
                if(tempoAnterior + multiplier * intervalo <= now)
                {
                    
                    timePassed += 1;
                    
                    //System.out.println(timePassed);
                    tempoAnterior = now;
                    rngArrival = rng.nextInt(maxArrival + 1);
                    rngResult = rng.nextDouble();
                    //rngArrival = 2;
                    if(isFullRandom)
                    {
                        if(rngResult < ySaida)
                        {
                            for(int i = 0 ; i < rngArrival ; i++)
                            {
                                //System.out.println("set");
                                fila.get();
                            }
                        }
                    }
                    if(rngResult <= Exponential(ySaida * multiplier, rngArrival))
                    {
                        for(int i = 0 ; i < rngArrival ; i++)
                        {
                            fila.get();
                        }   
                    }
                }
                
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private double Exponential(double y, double x)
    {
        return y * Math.exp(-y * x);
        //return 1 - Math.pow(Math.E, -y*x);
    }
}