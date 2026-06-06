package Clases;

import clases.Conexion;
//import  clases.Balanza;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanzaDAO {

    // Método que asume que manejas objetos de conexión externos o globales
    public List<Balanza> generarBalanza(String fechaInicio, String fechaFin, boolean soloConSaldo) {
        List<Balanza> lista = new ArrayList<>();
        
        // Query que calcula dinámicamente:
        // 1. Saldo Inicial: Movimientos acumulados antes de la 'fechaInicio' según naturaleza.
        // 2. Cargos y Abonos: Sumas dentro del rango de fechas.
        // 3. Saldo Final: Cálculo aritmético final según la naturaleza de la cuenta.
        String sql = "SELECT " +
                "    c.codigo, " +
                "    c.nombre, " +
                "    CASE tc.naturaleza " +
                "        WHEN 'Deudora' THEN COALESCE(SUM(CASE WHEN a.fecha < ? THEN p.debe - p.haber ELSE 0 END), 0.00) " +
                "        WHEN 'Acreedora' THEN COALESCE(SUM(CASE WHEN a.fecha < ? THEN p.haber - p.debe ELSE 0 END), 0.00) " +
                "    END AS saldo_inicial, " +
                "    COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.debe ELSE 0 END), 0.00) AS cargos, " +
                "    COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.haber ELSE 0 END), 0.00) AS abonos, " +
                "    CASE tc.naturaleza " +
                "        WHEN 'Deudora' THEN " +
                "            (CASE tc.naturaleza WHEN 'Deudora' THEN COALESCE(SUM(CASE WHEN a.fecha < ? THEN p.debe - p.haber ELSE 0 END), 0.00) END) " +
                "            + COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.debe ELSE 0 END), 0.00) " +
                "            - COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.haber ELSE 0 END), 0.00) " +
                "        WHEN 'Acreedora' THEN " +
                "            (CASE tc.naturaleza WHEN 'Acreedora' THEN COALESCE(SUM(CASE WHEN a.fecha < ? THEN p.haber - p.debe ELSE 0 END), 0.00) END) " +
                "            - COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.debe ELSE 0 END), 0.00) " +
                "            + COALESCE(SUM(CASE WHEN a.fecha BETWEEN ? AND ? THEN p.haber ELSE 0 END), 0.00) " +
                "    END AS saldo_final " +
                "FROM cuentas c " +
                "JOIN tipos_cuenta tc ON c.tipo = tc.tipo " +
                "LEFT JOIN partidas p ON c.id = p.cuenta_id " +
                "LEFT JOIN asientos a ON p.asiento_id = a.id AND a.estado = 'Aprobado' " +
                "WHERE c.nivel = 3 " + // Condición obligatoria: sólo subcuentas operativas
                "GROUP BY c.id, c.codigo, c.nombre, tc.naturaleza " +
                "HAVING 1=1 ";

        if (soloConSaldo) {
            sql += " AND (saldo_inicial != 0 OR cargos != 0 OR abonos != 0 OR saldo_final != 0) ";
        }
        
        sql += " ORDER BY c.codigo ASC;";

        // Reemplaza 'TuConexion.getConexion()' por tu método real de conexión
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Asignación de parámetros ordenados para las subconsultas de fechas
            ps.setString(1, fechaInicio);
            ps.setString(2, fechaInicio);
            ps.setString(3, fechaInicio);
            ps.setString(4, fechaFin);
            ps.setString(5, fechaInicio);
            ps.setString(6, fechaFin);
            ps.setString(7, fechaInicio);
            ps.setString(8, fechaInicio);
            ps.setString(9, fechaFin);
            ps.setString(10, fechaInicio);
            ps.setString(11, fechaFin);
            ps.setString(12, fechaInicio);
            ps.setString(13, fechaInicio);
            ps.setString(14, fechaFin);
            ps.setString(15, fechaInicio);
            ps.setString(16, fechaFin);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Balanza fila = new Balanza();
                    fila.setCodigo(rs.getString("codigo"));
                    fila.setNombreCuenta(rs.getString("nombre"));
                    fila.setSaldoInicial(rs.getBigDecimal("saldo_inicial"));
                    fila.setCargos(rs.getBigDecimal("cargos"));
                    fila.setAbonos(rs.getBigDecimal("abonos"));
                    fila.setSaldoFinal(rs.getBigDecimal("saldo_final"));
                    lista.add(fila);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al generar la balanza: " + e.getMessage());
        }
        return lista;
    }
}