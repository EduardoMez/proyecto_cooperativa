
package Clases;
/**
 *
 * @author luisc
 */
public class Cuenta {
  
    private int id;
    private String codigo;
    private String nombre;
    private String tipo;        // Activo, Pasivo, etc.
    private String naturaleza;  // Deudora o Acreedora
    private String clasificacion; // Balance o Resultado
    private int nivel;
    private boolean activa;

    public Cuenta() {
    }

    public Cuenta(int id, String codigo, String nombre, String tipo, String naturaleza, String clasificacion, int nivel, boolean activa) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.naturaleza = naturaleza;
        this.clasificacion = clasificacion;
        this.nivel = nivel;
        this.activa = activa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    

}
