
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
        // Modificamos la consulta para aplicar la función SHA2 a la contraseña ingresada
        // y asegurar que el usuario se encuentre activo (activo = 1)
        String sql = """
            SELECT u.id, u.nombre, u.rol
            FROM usuarios u
            WHERE u.nombre = ?
            AND u.password_hash = SHA2(?, 256)
            AND u.activo = 1
        """;

        try (
                Connection con = Conexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, nombre);
            ps.setString(2, password_hash); // Aquí sigues pasando la clave en texto plano, la BD se encarga de encriptarla para comparar
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setRol(rs.getString("rol"));
                    return usuario;
                }
            }

        } catch (Exception e) {
            System.out.println("Error en el proceso de autenticación: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
