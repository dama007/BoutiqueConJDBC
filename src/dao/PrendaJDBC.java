
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    
    public double valoracionCostes() {
        double valoracion = 0;
        conectar();
        if (conexion != null) {
            try {
                String query = "select sum(stock * coste) as valoracion from prenda";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    valoracion = rs.getDouble(1);
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Hay fallo" + ex.getMessage());
            } finally {
                desconectar();
            }
        }
        return valoracion;
    }
    
    
    public int totalStock() {
        int contador = 0;
        conectar();
        if (conexion != null) {
            try {
                String query = "select sum(stock) as stockTotal from prenda";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    contador = rs.getInt("stockTotal");
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Ha occurido un fallo" + ex.getMessage());
            } finally {
                desconectar();
            }
        }
        return contador;
    }
    
    
    public Prenda seleccionarPrenda(String codigo){
        conectar();
        Prenda pe=new Prenda();
        if (conexion !=null){
            try {
                String query ="select * from prenda where codigo='" + codigo + "'";
                Statement st=conexion.createStatement();
                ResultSet rs=st.executeQuery(query);
                while (rs.next()){
                    
                    pe.setCodigo(rs.getString("codigo"));  
                    pe.setDescripcion(rs.getString("descripcion"));
                    pe.setCoste(rs.getDouble("coste"));
                    pe.setColor(rs.getString("color"));
                    pe.setTalla(rs.getString("talla"));
                    pe.setPvp(rs.getDouble("pvp"));
                    pe.setStock(rs.getInt("stock"));
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Error en la consulta "+ex.getMessage());
            }finally{
                desconectar();
            }
        }
        return pe;
    }
    
    
    public boolean bajaPrenda(String codigo) {
        boolean ok = false;
        conectar();
        if (conexion != null) {
            
            try {
                String delete = "delete from prenda where codigo = '"+ codigo +"'";
                Statement st = conexion.createStatement();
                st.executeUpdate(delete);
                st.close();
                ok = true;
            } catch (SQLException ex) {
                System.out.println("Error al borrar: " + ex.getLocalizedMessage());
            } finally {
                desconectar();
            }
            
        }
        return ok;
    }
    
    
    
    public boolean modificarPrenda(Prenda p) {
        boolean ok = false;
        conectar();
        if (conexion != null) {
            try {
                String query = "update prenda set descripcion='" + p.getDescripcion() +
                        "', coste='" + p.getCoste() + "', color='" + p.getColor() +
                        "', talla='" + p.getTalla() + "', pvp='" + p.getPvp() +
                        "', stock='" + p.getStock() + " where codigo=" + p.getCodigo() + ";";
                Statement st = conexion.createStatement();
                st.executeQuery(query);
                st.close();
                ok = true;
            } catch (SQLException ex) {
                System.out.println("Hay un fallo" + ex.getMessage());
            } finally {
                desconectar();
            }
                    
        }
        return true;
    }
    
    
    public boolean existePrenda(String codigo) {
        conectar();
        if (conexion != null) {
            try {
                String query = "select * from prenda where codigo ='" + codigo + "'";
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
    
    
    public boolean modificarStock(Prenda p) {
        boolean ok = false;
        conectar();
        if (conexion != null) {
            try {
                String query = "update prenda set stock=" + p.getStock() + "where codigo=" + p.getCodigo() + ";";
                Statement st = conexion.createStatement();
                st.executeUpdate(query);
                st.close();
                ok = true;
            } catch (SQLException ex){
                System.out.println("Hay error" + ex.getMessage());
            } finally {
                desconectar();
            }
        }
        return ok;
    }
    
    
    public ListaPrendas prendasColor(String color){
        ListaPrendas ListaP=new ListaPrendas();
        conectar();
        if (conexion !=null){
            try {
                String query ="select * from prenda where color='" + color + "' order by descripcion, talla";
                Statement st=conexion.createStatement();
                ResultSet rs=st.executeQuery(query);
                while (rs.next()){
                    Prenda pe=new Prenda();
                    pe.setCodigo(rs.getString("codigo"));  // =  rs.getString(1)
                    pe.setDescripcion(rs.getString("descripcion"));
                    pe.setCoste(rs.getDouble("coste"));
                    pe.setColor(rs.getString("color"));
                    pe.setTalla(rs.getString("talla"));                
                    pe.setPvp(rs.getDouble("pvp"));
                    pe.setStock(rs.getInt("stock"));
                    ListaP.altaPrenda(pe);
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Error en la consulta "+ex.getMessage());
            }finally{
                desconectar();
            }
        }
        return ListaP;
    }
    
    
    
    
    public ArrayList<String> seleccionarColores() {
        ArrayList<String> colores = new ArrayList<>();
        conectar();
        if (conexion != null) {
            try {
                String query = "select distinct color from prenda order by color";
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    colores.add(rs.getString(1));
                }
                rs.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println("Hay un fallo" + ex.getMessage());
            } finally {
                desconectar();
            }
        }
        return colores;
    }
    
    
    public int totalPrendas(){
        int contador=0;
        conectar();
        if (conexion != null){
            try {
                String query = "select count(*) as total from prenda";
                Statement st=conexion.createStatement();
                ResultSet rs=st.executeQuery(query);
                
                if (rs.next()){
                    contador = rs.getInt(1);
                }
                rs.close();
                st.close();                
            } catch (SQLException ex) {
                System.out.println("Hay fallo" + ex.getMessage());
            }finally{
                desconectar();
            }        
        }
        return contador;
    }
    
    
    
    
    private void conectar() {
        try {
            String url = "jdbc:mysql://localhost:3306/muylgualboutique";
            String usr = "root";
            String password = "jeveris";
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
}
