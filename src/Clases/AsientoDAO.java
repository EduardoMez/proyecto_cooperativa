package Clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    /**
     * Busca asientos dentro de un rango de fechas.
     */
    public List<Asiento> buscarPorFechas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        List<Asiento> lista = new ArrayList<>();
        String sql = "SELECT id, numero, fecha, concepto, periodo_id, tipo, estado, creado_por FROM asientos "
                   + "WHERE fecha BETWEEN ? AND ? ORDER BY fecha ASC, numero ASC";
        
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asiento a = new Asiento();
                    // Usamos el setPeriodo_id temporalmente para guardar la PK real 'id' de la tabla,
                    // la cual necesitaremos obligatoriamente para buscar los renglones (Partidas)
                    a.setPeriodo_id(rs.getInt("id")); 
                    a.setNumero(rs.getString("numero"));
                    a.setFecha(rs.getDate("fecha"));
                    a.setConcepto(rs.getString("concepto"));
                    a.setTipo(rs.getString("tipo"));
                    a.setEstado(rs.getString("estado"));
                    a.setCreado_por(rs.getInt("creado_por"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar asientos por fecha: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Modifica únicamente el estado de un asiento específico usando su número correlativo.
     */
    public boolean actualizarEstado(String numeroAsiento, String nuevoEstado) {
        String sql = "UPDATE asientos SET estado = ? WHERE numero = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nuevoEstado);
            ps.setString(2, numeroAsiento);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del asiento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}