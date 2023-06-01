/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import java.util.ArrayList;

/**
 * operaciones para poder buscar una solucion al problema
 * @author robanac
 * compartir información entre entidades
 * A --->  B
 * A Intercambio de información entre entidades
 * B Competencia entre entidades ()
 * C Un conjunto de entidades donde se puedan implementar las anteriores
 * D proceso iterativo j que repita lo anterior 
 */
public class Heuristica {
    
    
    
    
    public void buscarSolucion(int n, int j, int ne){
        ArrayList<Entidad> conjuntoActual = new ArrayList<>();
        // inicializar el conjunto de entidades
        iniciarEntidades(conjuntoActual, ne, n);
        // ciclo que represente el proceso iterativo
        iteraciones:for(int x=0; x<j;x++ ){
            // objetivo: la generación de entidades cada vez 
            // mas prometedoras
            // generar un proceso de creación de entidades con respecto
            // a las inmediatamente anteriores
            ArrayList<Entidad> conjuntoNuevo  = new ArrayList<>();
            int [] criterio = Herramientas.generadorCriterios(n);
            for(int y=0; y < ne;y++){
                // seleccion de entidades
                Entidad mejor = Herramientas.seleccionMejor(conjuntoActual);
                Entidad aleatoria = Herramientas.seleccionAleatoria(conjuntoActual);
                // a raíz del intercambio obtendremos una nueva entidad
                Entidad nuevaEntidad = Herramientas.intercambioEntidades(criterio,aleatoria,mejor);
                // agregamos la nueva entidad
                conjuntoNuevo.add(nuevaEntidad);
                
            }
            // del nuevo conjunto voy a imprimir los datos del mejor
            Entidad mejor = Herramientas.seleccionMejor(conjuntoNuevo);
            System.out.println("x: "+x+" A:"+mejor.getAtaques());
            if(mejor.getAtaques()==0){
            break iteraciones;
            }
            // una vez completado el conjunto nuevo, hacemos la sustitución
            actualizacionConjunto(conjuntoActual,conjuntoNuevo);
        }
    
    
    }

    private void iniciarEntidades(ArrayList<Entidad> conjunto,
            int ne, int n) {
        for(int x=0; x<ne;x++){
            conjunto.add(new Entidad(n));
        }
    }

    private void actualizacionConjunto(ArrayList<Entidad> conjuntoActual,
            ArrayList<Entidad> conjuntoNuevo) {
        conjuntoActual = new ArrayList<>();
        
        for(Entidad aux: conjuntoNuevo){
            conjuntoActual.add(new Entidad(aux));
        }
    }
    
    
    
    
}
