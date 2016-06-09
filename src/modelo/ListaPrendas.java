/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import org.jdesktop.observablecollections.*;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author usu21
 */
public class ListaPrendas {
    
    private ObservableList<Prenda> lista;

    
    
    public ListaPrendas() {
        lista = ObservableCollections.observableList(new ArrayList<Prenda>());
    }
    
    public void altaPrenda(Prenda p) {
        lista.add(p);
    }
    
    public void bajaPrenda(Prenda p) {
        lista.remove(p);
    }
    
    
    

    public static final String PROP_LISTA = "lista";

    public ObservableList<Prenda> getLista() {
        return lista;
    }

    public void setLista(ObservableList<Prenda> lista) {
        ObservableList<Prenda> oldLista = this.lista;
        this.lista = lista;
        propertyChangeSupport.firePropertyChange(PROP_LISTA, oldLista, lista);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
