package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}