/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import java.util.Random;

/**
 * El objetivo de la entidad es que represente la posicion de en la que 
 * tienen que estar colocadas las damas (propuesta), ademas me dirá cuantos
 * ataques conlleva 
 * @author roban
 */
public class Entidad {
    
    private int [] estado;
    private int ataques;
    
    // aleatorio
    public Entidad(int n ) {
        this.estado = new int[n];
        iniciarAleatoriamente();
        calcularNumeroAtaques();
    }
    
     // argumento: estados
    public Entidad(int []estado) {
        this.estado = estado.clone();
        calcularNumeroAtaques();
    }
    
    public Entidad (Entidad aux){
        this.estado = aux.getEstado().clone();
        this.ataques = aux.getAtaques();
    
    }
    
   

    private void iniciarAleatoriamente() {
        Random ran = new Random();
        for(int x=0; x < this.getEstado().length;x++){
            this.estado[x]= ran.nextInt(this.getEstado().length);
        }
    }

    private void calcularNumeroAtaques() {
        
         for(int x = 0; x < this.getEstado().length; x++){
            for(int y = 0; y < this.getEstado().length; y++){
                if(x == y){
                    //break;
                }
                if(x != y){
                    if(this.getEstado()[x] == this.getEstado()[y]){
                        ataques++;
                    }
                    int valor = x-y;
                    for(int z = 0; z < this.getEstado().length; z++){
                        if(Math.abs(valor) == z){
                            int valor1 = Math.abs(getEstado()[x]-getEstado()[y]);
                            if(valor1 == z){
                                ataques++;
                            }
                        }
                    }
                
                }
        
            }
        }
    }

    /**
     * @return the estado
     */
    public int[] getEstado() {
        return estado;
    }

    /**
     * @return the ataques
     */
    public int getAtaques() {
        return ataques;
    }
    
}
