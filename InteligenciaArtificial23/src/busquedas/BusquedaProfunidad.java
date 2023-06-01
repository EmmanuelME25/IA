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
public class BusquedaProfunidad {
    
    private ArrayList<Nodo>grafo;
    private Pila pila;
    private ArrayList<Integer> ruta;

    public BusquedaProfunidad(ArrayList<Nodo>grafo) {
        this.grafo = grafo;
        this.pila = new Pila();
        this.ruta = new ArrayList<>();
    }
    
    public void generarRuta(int idi){
        // agregamos el elemento inicial
        int pos = this.grafo.indexOf(idi);
        this.grafo.get(pos).setVisitado(true);
        // mostramos
        this.ruta.add(this.grafo.get(pos).getId());
        //this.pila.enPilar(idi);
        // procesos iterativo
        do {
            // consultamos ruta
            int ultimoRuta = this.ruta.get(this.ruta.size()-1);
                   
            expandeUltimo(ultimoRuta);
            // desenpila
            int agregar = this.pila.desenPilar();
            // agregamos a ruta 
            this.ruta.add(agregar);
            
        }while(!this.pila.getPila().isEmpty());
        
        
        System.out.println();
        
    }

    private void expandeUltimo(int ultimoRuta) {
       //  enpilamos si no esta visitado
       // recorrer con los que esta conectado
       int pos = this.grafo.indexOf(new Nodo(ultimoRuta));
       Nodo nodo = this.grafo.get(pos);
       for(Integer aux: nodo.getCaminos()){
           int p = this.grafo.indexOf(new Nodo(aux));
           if(!this.grafo.get(p).isVisitado()){
               this.pila.enPilar(aux);
           }
           this.grafo.get(p).setVisitado(true);
       }
       
    }
    
    
    
}
