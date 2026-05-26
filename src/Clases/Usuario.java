package Clases;

/**
 *
 * @author aldan
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private String email;
     private String password_hash;
     private String rol;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, String password_hash, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password_hash = password_hash;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
     
      @Override
    public String toString(){
        return this.nombre+" "+" - "+this.rol;
    }
                
    
    
}
