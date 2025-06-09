
import java.util.*;

public class Hospital {
    private Map<String, Paciente> pacientesTotales = new HashMap<>();
    private PriorityQueue<Paciente> colaAtencion = new PriorityQueue<>();
    private Map<String, AreaAtencion> areasAtencion = new HashMap<>();
    private List<Paciente> pacientesAtendidos = new ArrayList<>();

    public Hospital() {
        areasAtencion.put("SAPU", new AreaAtencion("SAPU", 100));
        areasAtencion.put("urgencia_adulto", new AreaAtencion("urgencia_adulto", 100));
        areasAtencion.put("infantil", new AreaAtencion("infantil", 100));
    }

    public void registrarPaciente(Paciente p) {
        pacientesTotales.put(p.getId(), p);
        colaAtencion.offer(p);
        areasAtencion.get(p.getArea()).ingresarPaciente(p);
    }

    public void reasignarCategoria(String id, int nuevaCategoria) {
        Paciente p = pacientesTotales.get(id);
        if (p != null) {
            p.registrarCambio("Cambio de categoría de C" + p.getCategoria() + " a C" + nuevaCategoria);
            p.setCategoria(nuevaCategoria);
        }
    }

    public Paciente atenderSiguiente() {
        Paciente p = colaAtencion.poll();
        if (p != null) {
            p.setEstado("atendido");
            pacientesAtendidos.add(p);
        }
        return p;
    }

    public List<Paciente> obtenerPacientesPorCategoria(int categoria) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente p : colaAtencion) {
            if (p.getCategoria() == categoria) resultado.add(p);
        }
        return resultado;
    }

    public AreaAtencion obtenerArea(String nombre) {
        return areasAtencion.get(nombre);
    }

    public List<Paciente> getPacientesAtendidos() {
        return pacientesAtendidos;
    }
}
