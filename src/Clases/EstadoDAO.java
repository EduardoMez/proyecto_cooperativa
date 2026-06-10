package Clases;
/**
 *
 * @author luisc
 */

import clases.Conexion;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EstadoDAO {
 
    public List<Estado> obtenerSaldos(
            String fechaInicio,
            String fechaFin) {

        List<Estado> lista = new ArrayList<>();

        String sql =
        "SELECT c.codigo, c.nombre, c.tipo, " +
        "CASE tc.naturaleza " +
        "WHEN 'Deudora' THEN " +
        "COALESCE(SUM(CASE WHEN a.fecha <= ? THEN p.debe-p.haber ELSE 0 END),0) " +
        "WHEN 'Acreedora' THEN " +
        "COALESCE(SUM(CASE WHEN a.fecha <= ? THEN p.haber-p.debe ELSE 0 END),0) " +
        "END saldo " +
        "FROM cuentas c " +
        "JOIN tipos_cuenta tc ON tc.tipo = c.tipo " +
        "LEFT JOIN partidas p ON p.cuenta_id = c.id " +
        "LEFT JOIN asientos a ON a.id = p.asiento_id " +
        "AND a.estado='Aprobado' " +
        "WHERE c.nivel = 3 " +
        "GROUP BY c.id,c.codigo,c.nombre,c.tipo,tc.naturaleza " +
        "ORDER BY c.codigo";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fechaFin);
            ps.setString(2, fechaFin);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Estado e = new Estado();

                e.setCodigo(rs.getString("codigo"));
                e.setNombre(rs.getString("nombre"));
                e.setTipo(rs.getString("tipo"));
                e.setSaldo(rs.getBigDecimal("saldo"));

                lista.add(e);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    
}
