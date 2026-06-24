package Clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CierresDAO {

    // 1. Obtener nombres de los periodos que están abiertos
    public List<String> obtenerPeriodosAbiertos() throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM periodos WHERE estado = 'Abierto'";
        
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        }
        return lista;
    }

public CierreTemporalDTO calcularTotalesCierre(String nombrePeriodo) throws SQLException {
        CierreTemporalDTO dto = new CierreTemporalDTO();
        
        // Hacemos un LEFT JOIN con asientos y periodos para inspeccionar qué está pasando realmente
        String sql = "SELECT c.codigo, c.nombre AS cuenta, a.estado, per.nombre AS periodo, " +
                     "       COALESCE(p.debe, 0.00) AS d, COALESCE(p.haber, 0.00) AS h " +
                     "FROM partidas p " +
                     "JOIN cuentas c ON p.cuenta_id = c.id " +
                     "JOIN asientos a ON p.asiento_id = a.id " +
                     "JOIN periodos per ON a.periodo_id = per.id " +
                     "WHERE LOWER(TRIM(per.nombre)) = LOWER(TRIM(?))";
                     
        System.out.println("--- ENVIANDO A LA BD EL PERIODO: [" + nombrePeriodo + "] ---");
                     
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, nombrePeriodo);
            try (ResultSet rs = ps.executeQuery()) {
                boolean hayDatos = false;
                while (rs.next()) {
                    hayDatos = true;
                    String codigo = rs.getString("codigo");
                    String cuenta = rs.getString("cuenta");
                    String estado = rs.getString("estado");
                    double debe = rs.getDouble("d");
                    double haber = rs.getDouble("h");
                    
                    // Esto imprimirá en la consola interna de NetBeans cada partida encontrada
                    System.out.println(String.format("Cuenta: %s (%s) | Estado: %s | Debe: %.2f | Haber: %.2f", 
                            codigo, cuenta, estado, debe, haber));
                    
                    if (codigo != null && !codigo.isEmpty()) {
                        String primerDigito = codigo.trim().substring(0, 1);
                        
                        // Solo sumamos si el asiento está en un estado válido
                        if (estado != null && (estado.equalsIgnoreCase("Aprobado") || estado.equalsIgnoreCase("Registrado"))) {
                            switch (primerDigito) {
                                case "1": dto.totalActivos += (debe - haber); break;
                                case "2": dto.totalPasivos += (haber - debe); break;
                                case "3": dto.totalPatrimonio += (haber - debe); break;
                                case "4": dto.totalIngresos += (haber - debe); break;
                                case "5": dto.totalGastos += (debe - haber); break;
                            }
                        }
                    }
                }
                if (!hayDatos) {
                    System.out.println("ALERTA: El query no devolvió ninguna fila para el periodo solicitado.");
                }
            }
            dto.excedente = dto.totalIngresos - dto.totalGastos;
        }
        return dto;
    }

// 3. Proceso Transaccional Maestro Corregido (Filtro estricto anti-valores cero)
    public boolean ejecutarTransaccionCierre(String nombrePeriodoActual, String nombreNuevoPeriodo, 
                                             CierreTemporalDTO datos, String observaciones, int idUsuario) {
        Connection conn = null;
        try {
            conn = Conexion.conectar();
            conn.setAutoCommit(false); // INICIA LA TRANSACCIÓN SEGURA

            // A. Encontrar el ID correspondiente al periodo que se va a cerrar
            int idPeriodoActual = -1;
            String sqlId = "SELECT id FROM periodos WHERE LOWER(TRIM(nombre)) = LOWER(TRIM(?)) AND estado = 'Abierto'";
            try (PreparedStatement ps = conn.prepareStatement(sqlId)) {
                ps.setString(1, nombrePeriodoActual);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) idPeriodoActual = rs.getInt("id");
                }
            }
            
            if (idPeriodoActual == -1) {
                throw new SQLException("No se encontró el periodo abierto seleccionado o ya fue cerrado.");
            }

            // B. Registrar la foto histórica en la tabla 'cierres'
            String sqlCierre = "INSERT INTO cierres (periodo_id, fecha_cierre, excedente, total_ingresos, " +
                               "total_gastos, total_activos, total_pasivos, total_patrimonio, observaciones, generado_por) " +
                               "VALUES (?, CURDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlCierre)) {
                ps.setInt(1, idPeriodoActual);
                ps.setDouble(2, datos.excedente);
                ps.setDouble(3, datos.totalIngresos);
                ps.setDouble(4, datos.totalGastos);
                ps.setDouble(5, datos.totalActivos);
                ps.setDouble(6, datos.totalPasivos);
                ps.setDouble(7, datos.totalPatrimonio);
                ps.setString(8, observaciones);
                ps.setInt(9, idUsuario);
                ps.executeUpdate();
            }

       // ====================================================================
            // MODIFICACIÓN DE LA SECCIÓN C: FLUJO AUTOINCREMENTAL PARA EL NÚMERO
            // ====================================================================
            String sqlAsiento = "INSERT INTO asientos (numero, fecha, concepto, periodo_id, tipo, estado, creado_por) " +
                                "VALUES (?, CURDATE(), ?, ?, 'Cierre', 'Aprobado', ?)";
            int idAsientoGenerado = -1;
            
            try (PreparedStatement ps = conn.prepareStatement(sqlAsiento, Statement.RETURN_GENERATED_KEYS)) {
                // Colocamos un valor transitorio "PENDIENTE" que se actualizará de inmediato
                ps.setString(1, "PENDIENTE"); 
                ps.setString(2, "Asiento automático de liquidación de cuentas de resultados.");
                ps.setInt(3, idPeriodoActual);
                ps.setInt(4, idUsuario);
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idAsientoGenerado = rs.getInt(1); // Recuperamos el ID asignado por MariaDB
                    }
                }
            }

            if (idAsientoGenerado == -1) {
                throw new SQLException("Fallo crítico: No se pudo recuperar el ID autoincremental del Asiento.");
            }

            // Actualizamos la columna 'numero' para que tome el mismo número limpio de la ID autoincrementable (Ej: "19")
            String sqlUpdateNumero = "UPDATE asientos SET numero = ? WHERE id = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateNumero)) {
                psUpdate.setString(1, String.valueOf(idAsientoGenerado));
                psUpdate.setInt(2, idAsientoGenerado);
                psUpdate.executeUpdate();
            }
            // ====================================================================

            // D. Liquidar Cuentas de Ingresos mandándolas al DEBE (Cuentas que inician con 4)
            // CORRECCIÓN: Filtro estricto en el HAVING para asegurar que el saldo neto sea > 0.00
            String sqlIngresos = "INSERT INTO partidas (asiento_id, cuenta_id, debe, haber) " +
                                 "SELECT ?, p.cuenta_id, (SUM(p.haber) - SUM(p.debe)), 0.00 " +
                                 "FROM partidas p " +
                                 "JOIN cuentas c ON p.cuenta_id = c.id " +
                                 "JOIN asientos a ON p.asiento_id = a.id " +
                                 "WHERE a.periodo_id = ? AND LEFT(c.codigo, 1) = '4' AND a.estado IN ('Aprobado', 'Registrado') " +
                                 "GROUP BY p.cuenta_id " +
                                 "HAVING (SUM(p.haber) - SUM(p.debe)) > 0.00";
            try (PreparedStatement ps = conn.prepareStatement(sqlIngresos)) {
                ps.setInt(1, idAsientoGenerado);
                ps.setInt(2, idPeriodoActual);
                ps.executeUpdate(); // Solo se ejecutará e insertará si hay cuentas con saldos mayores a cero
            }

            // E. Liquidar Cuentas de Gastos mandándolas al HABER (Cuentas que inician con 5)
            // CORRECCIÓN: Filtro estricto en el HAVING para asegurar que el saldo neto sea > 0.00
            String sqlGastos = "INSERT INTO partidas (asiento_id, cuenta_id, debe, haber) " +
                               "SELECT ?, p.cuenta_id, 0.00, (SUM(p.debe) - SUM(p.haber)) " +
                               "FROM partidas p " +
                               "JOIN cuentas c ON p.cuenta_id = c.id " +
                               "JOIN asientos a ON p.asiento_id = a.id " +
                               "WHERE a.periodo_id = ? AND LEFT(c.codigo, 1) = '5' AND a.estado IN ('Aprobado', 'Registrado') " +
                               "GROUP BY p.cuenta_id " +
                               "HAVING (SUM(p.debe) - SUM(p.haber)) > 0.00";
            try (PreparedStatement ps = conn.prepareStatement(sqlGastos)) {
                ps.setInt(1, idAsientoGenerado);
                ps.setInt(2, idPeriodoActual);
                ps.executeUpdate();
            }

// F. Registrar el excedente/pérdida neto en la cuenta patrimonial de resultados
            if (Math.abs(datos.excedente) > 0.001) {
                int idCuentaUtilidad = -1;
                String sqlBuscarCuenta = "SELECT id FROM cuentas WHERE codigo LIKE '3%' AND (nombre LIKE '%Utilidad%' OR nombre LIKE '%Excedente%' OR nombre LIKE '%Resultado%') LIMIT 1";
                try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sqlBuscarCuenta)) {
                    if (rs.next()) idCuentaUtilidad = rs.getInt("id");
                }
                
                if (idCuentaUtilidad == -1) idCuentaUtilidad = 51; 

                // Si es positivo (excedente) va al Haber. Si es negativo (pérdida) va al Debe.
                String sqlUtilidad;
                double montoFinal = Math.abs(datos.excedente); // Volvemos el monto positivo para la BD
                
                if (datos.excedente >= 0) {
                    // Excedente/Utilidad -> Cuenta de patrimonio aumenta en el Haber
                    sqlUtilidad = "INSERT INTO partidas (asiento_id, cuenta_id, debe, haber) VALUES (?, ?, 0.00, ?)";
                } else {
                    // Déficit/Pérdida -> Cuenta de patrimonio disminuye en el Debe
                    sqlUtilidad = "INSERT INTO partidas (asiento_id, cuenta_id, debe, haber) VALUES (?, ?, ?, 0.00)";
                }
                
                try (PreparedStatement ps = conn.prepareStatement(sqlUtilidad)) {
                    ps.setInt(1, idAsientoGenerado);
                    ps.setInt(2, idCuentaUtilidad);
                    ps.setDouble(3, montoFinal);
                    ps.executeUpdate();
                }
            }

            // G. Cambiar el estado del periodo actual a 'Cerrado'
            String sqlUpdateP = "UPDATE periodos SET estado = 'Cerrado', cerrado_en = NOW(), fecha_fin = CURDATE() WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateP)) {
                ps.setInt(1, idPeriodoActual);
                ps.executeUpdate();
            }

            // H. Abrir de inmediato el nuevo periodo digitado por el usuario
            String sqlNuevoP = "INSERT INTO periodos (nombre, fecha_inicio, estado, observaciones) VALUES (?, CURDATE(), 'Abierto', ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlNuevoP)) {
                ps.setString(1, nombreNuevoPeriodo);
                ps.setString(2, "Periodo aperturado automáticamente después del cierre.");
                ps.executeUpdate();
            }

            conn.commit(); // ÉXITO SEGURO: APLICAMOS CAMBIOS EN LA BD
            return true;

        } catch (SQLException e) {
            System.out.println("ERROR CRÍTICO EN LA TRANSACCIÓN DE CIERRE: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}