/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristicas;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author roban
 */
public class Herramientas {
    public static Entidad seleccionMejor(ArrayList<Entidad> conjuntoActual){
        // hipotesis en que el mejor del conjunto
        int mejor = 0;
        for(int x=0; x<conjuntoActual.size();x++){
            // preguntamos
            if(conjuntoActual.get(x).getAtaques()<
                    conjuntoActual.get(mejor).getAtaques()){
                mejor = x;
            }
        }
        
        return new Entidad(conjuntoActual.get(mejor));
    }
    
    public static Entidad seleccionAleatoria(ArrayList<Entidad> conjuntoActual){
        Random ran = new Random();
        int pos = ran.nextInt(conjuntoActual.size());
         return new Entidad(conjuntoActual.get(pos));
    }
    
    

    public static Entidad intercambioEntidades(Entidad mejor, Entidad aleatoria) {
       int[] posNueva = new int[mejor.getEstado().length];
       Random ran = new Random();
       for(int x=0; x<posNueva.length;x++){
           int res = ran.nextInt(2);
           if(res==0){
               posNueva[x]=mejor.getEstado()[x];
           }else{
               posNueva[x]=aleatoria.getEstado()[x];
           }
       }
       Entidad nueva = new Entidad(posNueva);
       if(nueva.getAtaques()<mejor.getAtaques() 
               && nueva.getAtaques()<aleatoria.getAtaques()) return nueva;
       
       if(mejor.getAtaques()<nueva.getAtaques() 
               && mejor.getAtaques()<aleatoria.getAtaques()) return mejor;
       //
       
       
       return aleatoria;
    }
    
    public static Entidad intercambioEntidades(int[]criterio,Entidad mejor, Entidad aleatoria) {
       
        int [] entidad1 = new int[mejor.getEstado().length];
        int [] entidad2 = new int[mejor.getEstado().length];
        
       
       for(int x=0; x<criterio.length;x++){
           if(criterio[x]==1){
               entidad1[x]=mejor.getEstado()[x];
               entidad2[x]=aleatoria.getEstado()[x];
           }else{
               entidad1[x]=aleatoria.getEstado()[x];
               entidad2[x]=mejor.getEstado()[x];
           }
       }
       Entidad nueva1 = new Entidad(entidad1);
       Entidad nueva2 = new Entidad(entidad2);
       if(nueva1.getAtaques()<nueva2.getAtaques()) return nueva1;
              
       
       return nueva2;
    }
    
    public static int[] generadorCriterios(int n){
        int[] criterio = new int[n];
        Random ran = new Random();
        for(int x=0; x<n;x++){
            criterio[x]=ran.nextInt(2);
        }
        return criterio;
    }
}
