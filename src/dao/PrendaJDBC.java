
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.ListaPrendas;
import modelo.Prenda;

/**
 *
 * @author usu21
 */
public class PrendaJDBC {
    
    private Connection conexion;
    
    public ListaPrendas selectAllPrendas() {
      ListaPrendas listaPrendas = new ListaPrendas();
      conectar();
      if (conexion != null) {
          try {
              String query = "select * from prenda";
              Statement st = conexion.createStatement();
              ResultSet rs = st.executeQuery(query);
              while (rs.next()) {
                  Prenda prenda = new Prenda();
                  prenda.setCodigo(rs.getString("codigo"));
                  prenda.setDescripcion(rs.getString("descripcion"));
                  prenda.setCoste(rs.getDouble("coste"));
                  prenda.setColor(rs.getString("color"));
                  prenda.setTalla(rs.getString("talla"));
                  prenda.setPvp(rs.getDouble("pvp"));
                  prenda.setStock(rs.getInt("stock"));
                  listaPrendas.altaPrenda(prenda);  
              }
              rs.close();
              st.close();
          } catch (SQLException ex) {
              System.out.println("Error en la consulta" + ex.getMessage());
          } finally {
              desconectar();
          }
      }
      return listaPrendas;
    }
    
    
    
    public boolean insertarPrenda(Prenda prenda) {
        conectar();
        if (conexion != null) {
            try {
                String insert = "insert into prenda values(?,?,?,?,?,?,?)";
                PreparedStatement ps = conexion.prepareStatement(insert);
                ps.setString(1, prenda.getCodigo());
                ps.setString(2, prenda.getDescripcion());
                ps.setDouble(3, prenda.getCoste());
                ps.setString(4, prenda.getColor());
                ps.setString(5, prenda.getTalla());
                ps.setDouble(6, prenda.getPvp());
                ps.setInt(7, prenda.getStock());
                ps.executeUpdate();
                ps.close();
                return true;
            } catch (SQLException ex) {
                System.out.println("Error al insertar: " + ex.getMessage());
                return false;
            } finally {
                desconectar();
            }
        } else {
            return false;
        }
    }
    
    
    private void conectar() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/muylgualboutique";
            String usr = "root";
            String password = "tambacounda";
            conexion = DriverManager.getConnection(url, usr, password);
        } catch (SQLException ex) {
            System.out.println("Error al conectar" + ex.getMessage());
        }
    }
    
    
    
    private void desconectar() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al desconectar" + ex.getMessage());
            conexion = null;
        }
    }
    
    
    public boolean existePrenda(String codigo) {
        conectar();
        if (conexion != null) {
            try {
                String query = "select * from prenda wher codigo ='" + codigo + "'";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                boolean existe = false;
                if (rs.next()) {
                    existe = true;
                }
                rs.close();
                st.close();
                return existe;
                
            } catch(SQLException ex) {
                System.out.println("Error al consultar " + ex.getMessage());
                return false;
            } finally {
                desconectar();
            }
        } else {
            return false;
        }
    }   
}
