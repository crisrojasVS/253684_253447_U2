import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Carrera> carreras = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion;
        do {
            System.out.println("\n===== CONTROL ESCOLAR =====");
            System.out.println("1. Ver carreras y alumnos");
            System.out.println("2. Consultar alumno por matrícula");
            System.out.println("3. Cambio de carrera");
            System.out.println("4. Agregar alumno a una carrera");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1: mostrarTodo(); break;
                case 2: consultarAlumno(); break;
                case 3: cambiarCarrera(); break;
                case 4: agregarAlumno(); break;
                case 0: System.out.println("Hasta luego."); break;
                default: System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    static void cargarDatosIniciales() {
        Carrera sistemas = new Carrera("Ingeniería en Sistemas");
        Carrera industrial = new Carrera("Ingeniería Ambienta;");
        Carrera mecatronica = new Carrera("Mecatrónica");

        sistemas.alta(new Alumno("Ana López", "A001", 3));
        sistemas.alta(new Alumno("Luis Pérez", "A002", 5));
        industrial.alta(new Alumno("María Gómez", "A003", 2));
        mecatronica.alta(new Alumno("Carlos Ruiz", "A004", 4));

        carreras.add(sistemas);
        carreras.add(industrial);
        carreras.add(mecatronica);
    }

    static void mostrarTodo() {
        for (Carrera c : carreras) {
            System.out.println("\n" + c.getNombre());
            c.mostrarAlumnos();
        }
    }

    static void consultarAlumno() {
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();

        for (Carrera c : carreras) {
            Alumno a = c.buscar(matricula);
            if (a != null) {
                System.out.println(a + " | Carrera: " + c.getNombre());
                return;
            }
        }
        System.out.println("No se encontró el alumno.");
    }

    static void cambiarCarrera() {
        System.out.print("Matrícula del alumno: ");
        String matricula = sc.nextLine();

        Carrera origen = null;
        for (Carrera c : carreras) {
            if (c.buscar(matricula) != null) {
                origen = c;
            }
        }
        if (origen == null) {
            System.out.println("No se encontró el alumno.");
            return;
        }

        System.out.println("Carreras disponibles:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        System.out.print("Número de carrera destino: ");
        int num = leerEntero();

        if (num < 1 || num > carreras.size()) {
            System.out.println("Carrera no válida.");
            return;
        }
        Carrera destino = carreras.get(num - 1);

        if (destino == origen) {
            System.out.println("El alumno ya pertenece a esa carrera.");
            return;
        }

        
        Alumno alumno = origen.baja(matricula);
        destino.alta(alumno);

        System.out.println("Cambio realizado: baja en " + origen.getNombre()
                + " y alta en " + destino.getNombre());
    }

    
    static void agregarAlumno() {
        System.out.println("Carreras disponibles:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
        System.out.print("Número de carrera: ");
        int num = leerEntero();

        if (num < 1 || num > carreras.size()) {
            System.out.println("Carrera no válida.");
            return;
        }
        Carrera carrera = carreras.get(num - 1);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Matrícula: ");
        String matricula = sc.nextLine().trim();
        if (matricula.isEmpty()) {
            System.out.println("La matrícula no puede estar vacía.");
            return;
        }

        for (Carrera c : carreras) {
            if (c.buscar(matricula) != null) {
                System.out.println("Ya existe un alumno con esa matrícula.");
                return;
            }
        }

        System.out.print("Cuatrimestre: ");
        int cuatrimestre = leerEntero();
        if (cuatrimestre < 1) {
            System.out.println("Cuatrimestre no válido.");
            return;
        }

        carrera.alta(new Alumno(nombre, matricula, cuatrimestre));
        System.out.println("Alumno agregado a " + carrera.getNombre() + ".");
    }

    static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}