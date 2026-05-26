
package Clases;

import clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author aldan
 */
public class UsuarioDAO {
    
    public Usuario login(String nombre, String password_hash) {
        String sql = """
            SELECT u.id, u.nombre,  u.rol
            FROM usuarios u
            WHERE u.nombre = ?
            AND u.password_hash = ?
        """;

        try (
                Connection con = Conexion.conectar();
                java.sql.PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, nombre);
            ps.setString(2, password_hash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
