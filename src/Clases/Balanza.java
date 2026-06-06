package Clases;

import java.math.BigDecimal;

public class Balanza {

    private String codigo;
    private String nombreCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal cargos; // Debe
    private BigDecimal abonos; // Haber
    private BigDecimal saldoFinal;

    public Balanza() {
        this.saldoInicial = BigDecimal.ZERO;
        this.cargos = BigDecimal.ZERO;
        this.abonos = BigDecimal.ZERO;
        this.saldoFinal = BigDecimal.ZERO;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getCargos() {
        return cargos;
    }

    public void setCargos(BigDecimal cargos) {
        this.cargos = cargos;
    }

    public BigDecimal getAbonos() {
        return abonos;
    }

    public void setAbonos(BigDecimal abonos) {
        this.abonos = abonos;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}
