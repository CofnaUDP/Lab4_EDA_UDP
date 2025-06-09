
import java.util.*;

public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String apellido;
    private String id;
    private int categoria;
    private long tiempoLlegada;
    private String estado;
    private String area;
    private Stack<String> historialCambios;

    public Paciente(String nombre, String apellido, String id, int categoria, long tiempoLlegada, String area) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.categoria = categoria;
        this.tiempoLlegada = tiempoLlegada;
        this.estado = "en_espera";
        this.area = area;
        this.historialCambios = new Stack<>();
    }

    public long tiempoEsperaActual() {
        return (System.currentTimeMillis() / 1000 - tiempoLlegada) / 60;
    }

    public void registrarCambio(String descripcion) {
        historialCambios.push(descripcion);
    }

    public String obtenerUltimoCambio() {
        return historialCambios.isEmpty() ? "Sin cambios" : historialCambios.pop();
    }

    public int getCategoria() { return categoria; }
    public void setCategoria(int nuevaCategoria) { this.categoria = nuevaCategoria; }
    public String getId() { return id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getArea() { return area; }
    public long getTiempoLlegada() { return tiempoLlegada; }

    @Override
    public int compareTo(Paciente otro) {
        if (this.categoria != otro.categoria) return Integer.compare(this.categoria, otro.categoria);
        return Long.compare(this.tiempoLlegada, otro.tiempoLlegada);
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " " + apellido + " | C" + categoria + " | " + area + " | " + estado;
    }
}
