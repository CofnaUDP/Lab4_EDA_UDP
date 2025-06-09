
import java.util.*;

public class AreaAtencion {
    private String nombre;
    private PriorityQueue<Paciente> pacientesHeap;
    private int capacidadMaxima;

    public AreaAtencion(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.pacientesHeap = new PriorityQueue<>();
    }

    public void ingresarPaciente(Paciente p) {
        if (!estaSaturada()) {
            pacientesHeap.offer(p);
        }
    }

    public Paciente atenderPaciente() {
        return pacientesHeap.poll();
    }

    public boolean estaSaturada() {
        return pacientesHeap.size() >= capacidadMaxima;
    }

    public List<Paciente> obtenerPacientesPorHeapSort() {
        List<Paciente> copia = new ArrayList<>(pacientesHeap);
        copia.sort(null);
        return copia;
    }
}
