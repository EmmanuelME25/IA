/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedas;

import java.util.ArrayList;

/**
 *
 * @author roban
 */
public class Pila {
    
    private ArrayList<Integer> pila;

    public Pila() {
        this.pila = new ArrayList<>();
    }
    public void enPilar(int aux){
        this.pila.add(aux);
    }
    
    public int desenPilar (){
        int valor = this.pila.get(this.pila.size()-1);
        pila.remove(this.pila.size()-1);
        return valor;
    }

    /**
     * @return the cola
     */
    public ArrayList<Integer> getPila() {
        return pila;
    }
    
    
    
}
