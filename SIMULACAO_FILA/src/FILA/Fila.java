/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FILA;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ludus
 */
public class Fila
{ 
    private List fila;
    private int conteudo;
    private boolean locked = false;
 
    public Fila()
    {
        fila = new LinkedList();
    }
    public void set(int valor)
    {
        
            locked = true;
            fila.add(valor);
            System.out.println("Produziu " + valor);    
            locked = false;
       
    }
    public int get()
    {
       
            locked = true;
            if(getSize() <= 0)
            {
                return -1;
            }
            conteudo = Integer.parseInt(fila.remove(0).toString()); 
            System.out.println("Consumiu: " + conteudo);
            locked = false;
            return conteudo;
        
    }
    public int getSize()
    {
        return fila.size();
    }
}
