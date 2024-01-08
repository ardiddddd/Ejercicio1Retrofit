package javi.ardid.diaz.modelos;

public class ToDo {

    private String nombreCorredor;
    private float tiempoCorredor;
    private float distanciaRecorrida;
    private boolean completado

    public ToDo(String nombreCorredor, float tiempoCorredor, float distanciaRecorrida) {
        this.nombreCorredor = nombreCorredor;
        this.tiempoCorredor = tiempoCorredor;
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }

    public float getTiempoCorredor() {
        return tiempoCorredor;
    }

    public void setTiempoCorredor(float tiempoCorredor) {
        this.tiempoCorredor = tiempoCorredor;
    }

    public float getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(float distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }
}
