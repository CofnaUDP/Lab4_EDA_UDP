
import java.util.*;

public class SimuladorUrgencia {
    private Hospital hospital;
    private List<Paciente> pacientes;
    private Map<Integer, Integer> atendidosPorCategoria = new HashMap<>();
    private Map<Integer, Long> acumuladorEspera = new HashMap<>();
    private List<Paciente> pacientesFueraDeTiempo = new ArrayList<>();
    private int contadorIngresos = 0;
    private int pacientesTotales;

    private final Map<Integer, Integer> tiempoMaximoCategoria = Map.of(
        1, 0,
        2, 30,
        3, 90,
        4, 180,
        5, Integer.MAX_VALUE
    );

    public SimuladorUrgencia(List<Paciente> pacientes) {
        this.pacientes = pacientes;
        this.pacientesTotales = pacientes.size();
        this.hospital = new Hospital();
    }

    public void simular(int pacientesPorDia) {
        int minuto = 0;
        int indexPaciente = 0;

        while (minuto <= 24 * 60) {
            if (minuto % 10 == 0 && indexPaciente < pacientes.size()) {
                Paciente nuevo = pacientes.get(indexPaciente++);
                hospital.registrarPaciente(nuevo);
                contadorIngresos++;
            }

            if (minuto % 15 == 0) {
                atenderPaciente();
            }

            if (contadorIngresos == 3) {
                atenderPaciente();
                atenderPaciente();
                contadorIngresos = 0;
            }

            minuto++;
        }

        mostrarResumen();
    }

    private void atenderPaciente() {
        Paciente p = hospital.atenderSiguiente();
        if (p != null) {
            long espera = (System.currentTimeMillis() / 1000 - p.getTiempoLlegada()) / 60;
            int cat = p.getCategoria();

            atendidosPorCategoria.put(cat, atendidosPorCategoria.getOrDefault(cat, 0) + 1);
            acumuladorEspera.put(cat, acumuladorEspera.getOrDefault(cat, 0L) + espera);

            if (espera > tiempoMaximoCategoria.get(cat)) {
                pacientesFueraDeTiempo.add(p);
            }
        }
    }

    private void mostrarResumen() {
        System.out.println("===== RESUMEN DE SIMULACIÓN =====");
        int totalAtendidos = hospital.getPacientesAtendidos().size();
        System.out.println("Total pacientes atendidos: " + totalAtendidos);

        for (int i = 1; i <= 5; i++) {
            int atendidos = atendidosPorCategoria.getOrDefault(i, 0);
            long totalEspera = acumuladorEspera.getOrDefault(i, 0L);
            long promedio = atendidos > 0 ? totalEspera / atendidos : 0;

            System.out.printf("C%d → Atendidos: %d | Espera promedio: %d min%n", i, atendidos, promedio);
        }

        System.out.println("Pacientes que excedieron tiempo máximo: " + pacientesFueraDeTiempo.size());
        for (Paciente p : pacientesFueraDeTiempo) {
            System.out.printf(" - %s | Categoría: C%d | Estado: %s%n", p.getId(), p.getCategoria(), p.getEstado());
        }
    }
}
