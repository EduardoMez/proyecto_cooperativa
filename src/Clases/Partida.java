package Clases;
/**
 *
 * @author aldan
 */
public class Partida {

    private int id;
private int asiento_id;
private int cuenta_id;
private double debe;
private double haber;

        public Partida() {
        }

    public Partida(int id, int asiento_id, int cuenta_id, double debe, double haber) {
        this.id = id;
        this.asiento_id = asiento_id;
        this.cuenta_id = cuenta_id;
        this.debe = debe;
        this.haber = haber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAsiento_id() {
        return asiento_id;
    }

    public void setAsiento_id(int asiento_id) {
        this.asiento_id = asiento_id;
    }

    public int getCuenta_id() {
        return cuenta_id;
    }

    public void setCuenta_id(int cuenta_id) {
        this.cuenta_id = cuenta_id;
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
    
        




}
