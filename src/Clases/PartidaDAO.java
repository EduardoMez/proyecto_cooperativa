package Clases;

import clases.Conexion;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    // Este método ejecuta la inserción de una partida usando la conexión transaccional
    public void insertarPartida(Partida partida, Connection con) throws SQLException {
        String sql = """
            INSERT INTO partidas (asiento_id, cuenta_id, debe, haber) 
            VALUES (?, ?, ?, ?)
        """;
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, partida.getAsiento_id());
            ps.setInt(2, partida.getCuenta_id());
            ps.setDouble(3, partida.getDebe());
            ps.setDouble(4, partida.getHaber());
            
            ps.executeUpdate();
        }
    }
    
    /**
     * Obtiene el desglose de cuentas del asiento. 
     * NOTA: Ajusta los nombres de la tabla 'cuentas' y columnas 'codigo'/'nombre' según tu base de datos.
     */
    public List<Partida> obtenerPartidasPorAsientoId(int asientoId) {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.asiento_id, p.cuenta_id, p.debe, p.haber, c.codigo, c.nombre "
                   + "FROM partidas p "
                   + "INNER JOIN cuentas c ON p.cuenta_id = c.id "
                   + "WHERE p.asiento_id = ?";
        
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, asientoId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Partida p = new Partida();
                    p.setId(rs.getInt("id"));
                    p.setAsiento_id(rs.getInt("asiento_id"));
                    p.setCuenta_id(rs.getInt("cuenta_id"));
                    p.setDebe(rs.getDouble("debe"));
                    p.setHaber(rs.getDouble("haber"));
                    p.setCodigoCuentaTemporal(rs.getString("codigo"));
                    p.setNombreCuentaTemporal(rs.getString("nombre"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar los renglones del asiento: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
}