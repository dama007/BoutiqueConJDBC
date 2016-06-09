
package modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author usu21
 */
public class Prenda {
    
    private String codigo;    
    private String descripcion;    
    private double coste;   
    private String color;    
    private String talla; 
    private double pvp;
    private int stock;

    public Prenda() {
        codigo = "";
        descripcion = "";
        color = "";
        talla = "";
    }
    
    
    
    

    public static final String PROP_STOCK = "stock";

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        int oldStock = this.stock;
        this.stock = stock;
        propertyChangeSupport.firePropertyChange(PROP_STOCK, oldStock, stock);
    }

    
    

    public static final String PROP_PVP = "pvp";

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        double oldPvp = this.pvp;
        this.pvp = pvp;
        propertyChangeSupport.firePropertyChange(PROP_PVP, oldPvp, pvp);
    }


    public static final String PROP_TALLA = "talla";

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        String oldTalla = this.talla;
        this.talla = talla;
        propertyChangeSupport.firePropertyChange(PROP_TALLA, oldTalla, talla);
    }


    public static final String PROP_COLOR = "color";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        propertyChangeSupport.firePropertyChange(PROP_COLOR, oldColor, color);
    }


    public static final String PROP_COSTE = "coste";

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        double oldCoste = this.coste;
        this.coste = coste;
        propertyChangeSupport.firePropertyChange(PROP_COSTE, oldCoste, coste);
    }


    public static final String PROP_DESCRIPCION = "descripcion";

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        String oldDescripcion = this.descripcion;
        this.descripcion = descripcion;
        propertyChangeSupport.firePropertyChange(PROP_DESCRIPCION, oldDescripcion, descripcion);
    }


    public static final String PROP_CODIGO = "codigo";

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        propertyChangeSupport.firePropertyChange(PROP_CODIGO, oldCodigo, codigo);
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    
}
