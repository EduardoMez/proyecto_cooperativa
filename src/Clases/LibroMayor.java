
package Clases;
import java.util.Date;

/**
 *
 * @author luisc
 */
public class LibroMayor {
  
    private Date fecha;
    private String numeroAsiento;
    private String concepto;
    private double debe;
    private double haber;
    private double saldo;
    private int id;
    private String codigoCuenta;
    private String nombreCuenta;

    // Constructor vacío
    public LibroMayor() {
    }

    // Constructor con parámetros (opcional)
   public LibroMayor(int id, Date fecha, String numeroAsiento, String concepto, String codigoCuenta, String nombreCuenta,
            double debe, double haber, double saldo) {

        this.id = id;
        this.fecha = fecha;
        this.numeroAsiento = numeroAsiento;
        this.concepto = concepto;
        this.codigoCuenta = codigoCuenta;
        this.nombreCuenta = nombreCuenta;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
    }

    // GETTERS Y SETTERS

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public String getCodigoCuenta() {
    return codigoCuenta;
}

public void setCodigoCuenta(String codigoCuenta) {
    this.codigoCuenta = codigoCuenta;
}

public String getNombreCuenta() {
    return nombreCuenta;
}

public void setNombreCuenta(String nombreCuenta) {
    this.nombreCuenta = nombreCuenta;
}
}
