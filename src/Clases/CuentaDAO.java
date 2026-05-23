package Clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

/**
 *
 * @author luisc
 */
public class CuentaDAO {

   public List<Cuenta> listarCuentas() {
    List<Cuenta> lista = new ArrayList<>();

    String sql = """
        SELECT c.id, c.codigo, c.nombre, c.tipo,
               tc.naturaleza, tc.clasificacion,
               c.nivel, c.activa
        FROM cuentas c
        INNER JOIN tipos_cuenta tc ON c.tipo = tc.tipo
        ORDER BY c.codigo
        """;

    try (
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {

        while (rs.next()) {
            Cuenta c = new Cuenta();
            c.setId(rs.getInt("id"));
            c.setCodigo(rs.getString("codigo"));
            c.setNombre(rs.getString("nombre"));
            c.setTipo(rs.getString("tipo"));
            c.setNaturaleza(rs.getString("naturaleza"));
            c.setClasificacion(rs.getString("clasificacion"));
            c.setNivel(rs.getInt("nivel"));
            c.setActiva(rs.getBoolean("activa"));

            lista.add(c);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            null,
            "Error al listar cuentas:\n" + e.getMessage()
        );
    }

    return lista;
}
   
   
    public List<Cuenta> listarCuentasPorTipo(String tipo) {
    List<Cuenta> lista = new ArrayList<>();

    String sql = """
        SELECT c.id, c.codigo, c.nombre, c.tipo,
               tc.naturaleza, tc.clasificacion,
               c.nivel, c.activa
        FROM cuentas c
        INNER JOIN tipos_cuenta tc ON c.tipo = tc.tipo
        WHERE c.tipo = ?
        ORDER BY c.codigo
        """;

    try (
        Connection con = Conexion.conectar();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setString(1, tipo);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Cuenta c = new Cuenta();
            c.setId(rs.getInt("id"));
            c.setCodigo(rs.getString("codigo"));
            c.setNombre(rs.getString("nombre"));
            c.setTipo(rs.getString("tipo"));
            c.setNaturaleza(rs.getString("naturaleza"));
            c.setClasificacion(rs.getString("clasificacion"));
            c.setNivel(rs.getInt("nivel"));
            c.setActiva(rs.getBoolean("activa"));

            lista.add(c);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            null,
            "Error al listar cuentas:\n" + e.getMessage()
        );
    }

    return lista;
}
    
}
