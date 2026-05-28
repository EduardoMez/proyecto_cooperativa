package Clases;

import java.util.Date;

public class Periodo {
    private int id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String observaciones;

    public Periodo() {
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservations() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}