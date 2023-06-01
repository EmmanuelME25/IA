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
public class Nodo {
    
    private int id;
    private ArrayList<Integer>caminos;
    private boolean visitado;

    public Nodo(int id) {
        this.id = id;
        this.visitado = false;
        this.caminos = new ArrayList<>();
    }
    
    public void agregarConexion(int id){
        this.getCaminos().add(id);           
    
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the caminos
     */
    public ArrayList<Integer> getCaminos() {
        return caminos;
    }

    /**
     * @param caminos the caminos to set
     */
    public void setCaminos(ArrayList<Integer> caminos) {
        this.caminos = caminos;
    }

    /**
     * @return the visitado
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * @param visitado the visitado to set
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    @Override
    public boolean equals(Object o) {
        Nodo aux = (Nodo)o;
        if(aux.getId()==this.id)return true;
        
        return false;//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
