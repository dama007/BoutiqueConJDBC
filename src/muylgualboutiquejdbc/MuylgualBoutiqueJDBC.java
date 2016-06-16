
package muylgualboutiquejdbc;

import dao.PrendaJDBC;
import vista.MenuPrincipal;

/**
 *
 * @author usu21
 */
public class MuylgualBoutiqueJDBC {
    
    public static PrendaJDBC prendaJDBC;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        prendaJDBC = new PrendaJDBC();
        
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }
    
}
