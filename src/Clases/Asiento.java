/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;

/**
 *
 * @author aldan
 */
public class Asiento {
    
   private String numero;
 private java.util.Date fecha; // Cambiar de String a Date
   private String concepto;
   private int periodo_id;
   private String tipo;
     private String estado;
     private int creado_por;
     private String creado_en;

    public Asiento() {
    }

    public Asiento(String numero, Date fecha, String concepto, int periodo_id, String tipo, String estado, int creado_por, String creado_en) {
        this.numero = numero;
        this.fecha = fecha;
        this.concepto = concepto;
        this.periodo_id = periodo_id;
        this.tipo = tipo;
        this.estado = estado;
        this.creado_por = creado_por;
        this.creado_en = creado_en;
    }

    
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

  

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getPeriodo_id() {
        return periodo_id;
    }

    public void setPeriodo_id(int periodo_id) {
        this.periodo_id = periodo_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    public String getCreado_en() {
        return creado_en;
    }

    public void setCreado_en(String creado_en) {
        this.creado_en = creado_en;
    }
     
     
   
}
