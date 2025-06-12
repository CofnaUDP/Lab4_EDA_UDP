import java.util.*;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        long inicio = System.currentTimeMillis() / 1000;
        List<Paciente> pacientes = GeneradorPacientes.generar(144, inicio);
        GeneradorPacientes.guardarArchivo(pacientes, "Pacientes_24h.txt");

        SimuladorUrgencia simulador = new SimuladorUrgencia(pacientes);
        simulador.simular(144);

        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        System.out.println("Nombre\tApellido\tCategoría\tHoraLlegada\tHoraAtención\tEstado");

        for (Paciente p : pacientes) {
            Date horaLlegada = new Date(p.getTiempoLlegada() * 1000);
            String horaStr = formatoHora.format(horaLlegada);
            String estado = p.getEstado();
            System.out.printf("%s\t%s\t\tC%d\t\t%s\t\t%s\t\t%s\n",
                    p.getNombre(),
                    p.getApellido(),
                    p.getCategoria(),
                    horaStr,
                    "-",
                    estado);
        }
    }
}
