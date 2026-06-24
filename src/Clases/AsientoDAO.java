package Clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AsientoDAO {
    
    // Este método inserta el asiento usando la conexión controlada por la transacción
    public int insertarAsiento(Asiento asiento, Connection con) throws SQLException {
        String sql = """
            INSERT INTO asientos (numero, fecha, concepto, periodo_id, tipo, estado, creado_por) 
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        
        // Pasamos Statement.RETURN_GENERATED_KEYS para obtener el ID asignado por AUTO_INCREMENT
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, asiento.getNumero());
            // Convertimos java.util.Date a java.sql.Date para MariaDB
            ps.setDate(2, new java.sql.Date(asiento.getFecha().getTime()));
            ps.setString(3, asiento.getConcepto());
            
            // periodo_id puede ser nulo según tu captura
            if (asiento.getPeriodo_id() == 0) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, asiento.getPeriodo_id());
            }
            
            ps.setString(5, asiento.getTipo());
            ps.setString(6, asiento.getEstado());
            
            // creado_por puede ser nulo
            if (asiento.getCreado_por() == 0) {
                ps.setNull(7, java.sql.Types.INTEGER);
            } else {
                ps.setInt(7, asiento.getCreado_por());
            }
            
            ps.executeUpdate();
            
            // Recuperamos el ID generado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Retorna el ID real generado por la base de datos
                }
            }
        }
        throw new SQLException("No se pudo obtener el ID del asiento autogenerado.");
    }

    // Mantienes tu método anterior por si lo usas en otra parte visual
    public int obtenerIdAsiento() {
        int id = 1;
        String sql = "SELECT MAX(id) FROM asientos";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // Método para obtener el siguiente correlativo numérico de asiento
    public int obtenerSiguienteCorrelativo() {
        String sql = "SELECT COUNT(*) FROM asientos";
        try (Connection con = Conexion.conectar(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) + 1; // Retorna el conteo total + 1
            }
        } catch (SQLException e) {
            System.out.println("Error al calcular correlativo de asientos: " + e.getMessage());
            e.printStackTrace();
        }
        return 1; // Si está vacía o falla, empieza en 1
    }
    
}