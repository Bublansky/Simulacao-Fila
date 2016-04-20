/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Ludus
 */
public class MAIN
{
    
    
    public static void main(String[] args) throws FileNotFoundException
    {
        boolean RandomValues;
        Fila fila = new Fila();
        
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
            //System.out.println("Iniciado com valores totalmente aleatórios");
            RandomValues = false;
            System.out.println("Iniciado com entrada Poisson e saída exponencial");
        
        Produtor p = new Produtor(1, fila, 5, RandomValues, ds);
        Consumidor c = new Consumidor(1, fila, 2, RandomValues);
        p.start();
        c.start();
        
    }
}
