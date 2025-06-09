
import java.io.*;
import java.util.*;

public class GeneradorPacientes {
    private static final String[] NOMBRES = {"Ana", "Luis", "Pedro", "Josefa", "Carlos", "Camila", "Makoto", "Matias", "Nero", "Dante", "Tomás", "Teresa", "Juan", "Daniela", "Elena", "Martín", "Andrés", "Paula", "Rosario", "Javier", "Matías", "Valentina", "Laura", "Sebastián", "Bruno", "Lucía", "Renata", "Gabriel", "Felipe", "Antonia", "Agustina", "Nicolás", "Sofía", "Benjamín", "Simón", "Emilia", "Francisca", "Samuel", "Ignacio", "Isidora", "Catalina", "Joaquín"};
    private static final String[] APELLIDOS = {"Rojas", "Pérez", "Gómez", "Fernández", "Muñoz", "Silva", "Castillo", "Soto", "Vega", "Reyes", "Morales", "Fuentes", "Gutiérrez", "Cortés", "Carrasco", "Ortega", "Campos", "Araya", "Espinoza", "Navarro", "Contreras", "Romero", "Herrera", "Salazar", "Torres", "Loyola", "Paredes", "Godoy", "Vergara", "Bravo", "Tapia", "Figueroa", "Valenzuela", "Zúñiga", "Medina", "Sepúlveda", "Toledo", "Valdés", "Alarcón", "Cáceres"};
    private static final String[] AREAS = {"SAPU", "urgencia_adulto", "infantil"};
    private static Random rand = new Random();

    public static List<Paciente> generar(int cantidad, long timestampInicio) {
        List<Paciente> lista = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES[rand.nextInt(NOMBRES.length)];
            String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];
            String id = "P" + (1000 + i);
            int categoria = generarCategoria();
            long tiempoLlegada = timestampInicio + (i * 600);
            String area = AREAS[rand.nextInt(AREAS.length)];
            lista.add(new Paciente(nombre, apellido, id, categoria, tiempoLlegada, area));
        }
        return lista;
    }

    private static int generarCategoria() {
        int prob = rand.nextInt(100);
        if (prob < 10) return 1;
        if (prob < 25) return 2;
        if (prob < 43) return 3;
        if (prob < 70) return 4;
        return 5;
    }

    public static void guardarArchivo(List<Paciente> pacientes, String nombreArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Paciente p : pacientes) {
                bw.write(p.toString());
                bw.newLine();
            }
        }
    }
}
