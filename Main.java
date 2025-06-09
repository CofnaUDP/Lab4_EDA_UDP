
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis() / 1000;
        List<Paciente> pacientes = GeneradorPacientes.generar(144, inicio);
        GeneradorPacientes.guardarArchivo(pacientes, "Pacientes_24h.txt");

        SimuladorUrgencia simulador = new SimuladorUrgencia(pacientes);
        simulador.simular(144);
    }
}
