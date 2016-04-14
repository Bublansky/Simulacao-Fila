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
    private boolean disponivel = true;
 
    public Fila()
    {
        fila = new LinkedList();
    }
    public void set(int valor)
    {
        if(disponivel)
        {
            disponivel = false;
            fila.add(valor);
            System.out.println("Produziu " + valor);
            disponivel = true;
        }
        else
        {
            while (!disponivel)
            {
            }
            disponivel = false;
            fila.add(valor);
            System.out.println("Produziu " + valor);
            disponivel = true;
        }
    }
 
    public int get()
    {
        if(disponivel)
        {
            disponivel = false;
            conteudo = Integer.parseInt(fila.remove(0).toString()); 
            System.out.println("Consumiu: " + conteudo);
            disponivel = true;
            return conteudo;
        }
        else
        {
            while (!disponivel)
            {
            }
            disponivel = false;
            conteudo = Integer.parseInt(fila.remove(0).toString()); 
            System.out.println("Consumiu: " + conteudo);
            disponivel = true;
            return conteudo;
        }
    }
    public int getSize()
    {
        return fila.size();
    }
}
