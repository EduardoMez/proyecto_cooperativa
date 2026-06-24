package Clases;

import java.math.BigDecimal;

public class Cierres {

    private int id;
    private int periodo_id;
    private String fecha_cierre;
    private BigDecimal excedente;
    private BigDecimal total_ingresos;
    private BigDecimal total_gastos;
    private BigDecimal total_activos;
    private BigDecimal total_pasivos;
    private BigDecimal total_patrimonio;
    private String observaciones;
    private int generado_por;
    private String ejecutado_en;

    public Cierres() {
    }

    public Cierres(int id, int periodo_id, String fecha_cierre, BigDecimal excedente, BigDecimal total_ingresos, BigDecimal total_gastos, BigDecimal total_activos, BigDecimal total_pasivos, BigDecimal total_patrimonio, String observaciones, int generado_por, String ejecutado_en) {
        this.id = id;
        this.periodo_id = periodo_id;
        this.fecha_cierre = fecha_cierre;
        this.excedente = excedente;
        this.total_ingresos = total_ingresos;
        this.total_gastos = total_gastos;
        this.total_activos = total_activos;
        this.total_pasivos = total_pasivos;
        this.total_patrimonio = total_patrimonio;
        this.observaciones = observaciones;
        this.generado_por = generado_por;
        this.ejecutado_en = ejecutado_en;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeriodo_id() {
        return periodo_id;
    }

    public void setPeriodo_id(int periodo_id) {
        this.periodo_id = periodo_id;
    }

    public String getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public BigDecimal getExcedente() {
        return excedente;
    }

    public void setExcedente(BigDecimal excedente) {
        this.excedente = excedente;
    }

    public BigDecimal getTotal_ingresos() {
        return total_ingresos;
    }

    public void setTotal_ingresos(BigDecimal total_ingresos) {
        this.total_ingresos = total_ingresos;
    }

    public BigDecimal getTotal_gastos() {
        return total_gastos;
    }

    public void setTotal_gastos(BigDecimal total_gastos) {
        this.total_gastos = total_gastos;
    }

    public BigDecimal getTotal_activos() {
        return total_activos;
    }

    public void setTotal_activos(BigDecimal total_activos) {
        this.total_activos = total_activos;
    }

    public BigDecimal getTotal_pasivos() {
        return total_pasivos;
    }

    public void setTotal_pasivos(BigDecimal total_pasivos) {
        this.total_pasivos = total_pasivos;
    }

    public BigDecimal getTotal_patrimonio() {
        return total_patrimonio;
    }

    public void setTotal_patrimonio(BigDecimal total_patrimonio) {
        this.total_patrimonio = total_patrimonio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getGenerado_por() {
        return generado_por;
    }

    public void setGenerado_por(int generado_por) {
        this.generado_por = generado_por;
    }

    public String getEjecutado_en() {
        return ejecutado_en;
    }

    public void setEjecutado_en(String ejecutado_en) {
        this.ejecutado_en = ejecutado_en;
    }
    
    

}
