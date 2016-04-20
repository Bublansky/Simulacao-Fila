/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

import com.sun.xml.internal.ws.client.ContentNegotiation;
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
    private int intervalo = 1000; // 1 segundo
    private int rngRange = 20;
    private int intervaloAdd = 3000;
    private int timePassed = 0;
    private double y = 3.2;
    private double rngResult;
    private boolean isFullRandom = false;
    private DefaultCategoryDataset dataset;
    
    public Produtor(int id, Fila p, int producaoTotal, boolean fullRandom, DefaultCategoryDataset dataset)
    {
        idProdutor = id;
        fila = p;
        this.producaoTotal = producaoTotal;
        this.data = new Date();
        this.rng = new Random();
        isFullRandom = fullRandom;
        this.dataset = dataset;
    }
    /*
        3.2 clientes por segundo entram na fila
        y = 3.2
        0.526 clientes por segundo são atendidos
    */
    
    @Override
    public void run()
    {
        
        System.out.println("Produtor iniciado!");
        
        long lastTimeArrival, lastTimeExit, now;
        int multiplier = 1;
        int maxArrival = 5;
        int rngArrival;
        double yEntrada = 3.2;
        int indice = 1;
        
        now = new Date().getTime();
        
        
        JFreeChart grafico;
        tempoAnterior = now;
        while(true)
        {
            now = new Date().getTime();
            if(tempoAnterior + multiplier * intervalo <= now)
            {   
                timePassed += 1;
                if(timePassed % 5 == 0)
                {
                    System.out.println("Passaram " + timePassed + " segundos/ " 
                            + fila.getSize() + " pessoas na fila.");     
                }
                if (timePassed % 10 == 0)
                {
                    System.out.println("teste");
                    grafico = ChartFactory.createLineChart("Pessoas na fila", "Tempo", 
                            "Quantidade", dataset, PlotOrientation.VERTICAL, true, true, false);
                    OutputStream arquivo;
                    try {
                        arquivo = new FileOutputStream("teste.png");
                        ChartUtilities.writeChartAsPNG(arquivo, grafico, 550, 400);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //System.out.println(timePassed);
                tempoAnterior = now;
                rngArrival = rng.nextInt(maxArrival + 1);
                rngResult = rng.nextDouble();
                //rngArrival = 2;
                if(isFullRandom)
                {
                    if(rngResult < yEntrada)
                    {
                        for(int i = 0 ; i < rngArrival ; i++)
                        {
                            //System.out.println("set");
                            fila.set(indice++);   
                        }   
                    }
                }
                else
                {
                    if(rngResult <= Poisson(yEntrada, rngArrival))
                    {
                        for(int i = 0 ; i < rngArrival ; i++)
                        {
                            //System.out.println("set");
                            fila.set(indice++);   
                        }       
                    }
                }
                dataset.addValue(fila.getSize(), "", timePassed + "s");
            }
        }
    }
    private double Poisson(double y, int x)
    {
        return Math.pow(Math.E, -y) * Math.pow(y, x) / Fatorial(x);    
    }
    
    private int Fatorial(int x)
    {
        if(x == 0 || x == 1)
        {
            return 1;
        }
        return x * Fatorial(x - 1);
    }
}
