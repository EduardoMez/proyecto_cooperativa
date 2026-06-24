package Clases;

import clases.Conexion;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodoDAO {

    // Busca el periodo activo actual en el sistema
    public Periodo obtenerPeriodoActivo() {
        String sql = "SELECT id, nombre, estado FROM periodos WHERE estado = 'Abierto' LIMIT 1";
        
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                Periodo periodo = new Periodo();
                periodo.setId(rs.getInt("id"));
                periodo.setNombre(rs.getString("nombre"));
                periodo.setEstado(rs.getString("estado"));
                return periodo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no hay ningún periodo abierto configurado
    }
    
}