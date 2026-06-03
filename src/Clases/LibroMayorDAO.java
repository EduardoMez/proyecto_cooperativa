package Clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luisc
 */
public class LibroMayorDAO {

    public List<LibroMayor> listarLibroMayor(
        int cuentaId,
        java.sql.Date fechaInicio,
        java.sql.Date fechaFin,
        Connection con) throws SQLException {

    List<LibroMayor> lista = new ArrayList<>();

    String sql =
            "SELECT p.id, "
            + "a.numero, "
            + "a.fecha, "
            + "a.concepto, "
            + "c.codigo, "
            + "c.nombre, "
            + "p.debe, "
            + "p.haber "
            + "FROM partidas p "
            + "INNER JOIN asientos a ON p.asiento_id = a.id "
            + "INNER JOIN cuentas c ON p.cuenta_id = c.id "
            + "WHERE p.cuenta_id = ? "
            + "AND a.fecha BETWEEN ? AND ? "
            + "AND a.estado <> 'Anulado' "
            + "ORDER BY a.fecha, a.numero";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, cuentaId);
    ps.setDate(2, fechaInicio);
    ps.setDate(3, fechaFin);

    ResultSet rs = ps.executeQuery();

    double saldo = 0;

    while (rs.next()) {

        LibroMayor lm = new LibroMayor();

        lm.setId(rs.getInt("id"));
        lm.setNumeroAsiento(rs.getString("numero"));
        lm.setFecha(rs.getDate("fecha"));
        lm.setConcepto(rs.getString("concepto"));
        lm.setCodigoCuenta(rs.getString("codigo"));
        lm.setNombreCuenta(rs.getString("nombre"));
        lm.setDebe(rs.getDouble("debe"));
        lm.setHaber(rs.getDouble("haber"));

        // saldo acumulado
        saldo += rs.getDouble("debe")
                - rs.getDouble("haber");

        lm.setSaldo(saldo);

        lista.add(lm);
    }

    rs.close();
    ps.close();

    return lista;
}
}
