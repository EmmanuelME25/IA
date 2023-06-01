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
class Cola {
    
    private ArrayList<Integer> cola;

    public Cola() {
        this.cola = new ArrayList<>();
    }
    public void encolar(int aux){
        this.getCola().add(aux);
    }
    
    public int desencolar (){
        int valor = this.getCola().get(0);
        this.getCola().remove(0);
        return valor;
    }

    /**
     * @return the cola
     */
    public ArrayList<Integer> getCola() {
        return cola;
    }
    
    
    
}
