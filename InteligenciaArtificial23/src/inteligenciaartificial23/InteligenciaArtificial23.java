/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteligenciaartificial23;

import busquedas.BusquedaAnchura;
import busquedas.Nodo;
import heuristicas.Heuristica;
import java.util.ArrayList;

/**
 *
 * @author roban
 */
public class InteligenciaArtificial23 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Nodo> grafo = new ArrayList<>();
//        Nodo n0 = new Nodo(0);
//        n0.agregarConexion(1);
//        Nodo n1 = new Nodo(1);
//        n1.agregarConexion(4);
//        Nodo n2= new Nodo(2);
//        n2.agregarConexion(0);
//        // n2.agregarConexion(7);
//        Nodo n3= new Nodo(3);
//        n3.agregarConexion(1);
//        Nodo n4 = new Nodo(4);
//        n4.agregarConexion(2);
//        Nodo n5 = new Nodo(5);
//        n0.agregarConexion(5);
//        grafo.add(n0);
//        grafo.add(n1);
//        grafo.add(n2);
//        grafo.add(n3);
//        grafo.add(n4);
//        grafo.add(n5);
//        
           Heuristica h = new Heuristica();
           h.buscarSolucion(14, 1000000, 150);
        System.out.println();
    }
    
}
