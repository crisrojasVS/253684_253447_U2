import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private GestorEscolar gestor = new GestorEscolar();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n    CONTROL ESCOLAR    ");
            System.out.println("1. Ver carreras y alumnos");
            System.out.println("2. Consultar alumno por matrícula");
            System.out.println("3. Cambio de carrera");
            System.out.println("4. Agregar alumno");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");

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

    private void mostrarTodo() {
        for (Carrera c : gestor.getCarreras()) {
            System.out.println("\n" + c.getNombre());
            c.mostrarAlumnos();
        }
    }

    private void mostrarCarreras() {
        ArrayList<Carrera> carreras = gestor.getCarreras();
        System.out.println("Carreras disponibles:");
        for (int i = 0; i < carreras.size(); i++) {
            System.out.println((i + 1) + ". " + carreras.get(i).getNombre());
        }
    }

    private void consultarAlumno() {
        String matricula = leerTexto("Matrícula: ");
        Carrera carrera = gestor.buscarCarreraDeAlumno(matricula);

        if (carrera == null) {
            System.out.println("No se encontró el alumno.");
            return;
        }
        System.out.println(carrera.buscar(matricula)
                + " | Carrera: " + carrera.getNombre());
    }

    private void agregarAlumno() {
        String nombre = leerTexto("Nombre: ");
        String matricula = leerTexto("Matrícula: ");
        int cuatrimestre = leerEntero("Cuatrimestre actual: ");

        // Se valida en el momento, antes de pedir la carrera
        if (cuatrimestre < 1) {
            System.out.println("Cuatrimestre no válido.");
            return;
        }

        mostrarCarreras();
        int num = leerEntero("Número de carrera: ");

        System.out.println(gestor.agregarAlumno(nombre, matricula, cuatrimestre, num));
    }

    private void cambiarCarrera() {
        String matricula = leerTexto("Matrícula del alumno: ");

        if (gestor.buscarAlumno(matricula) == null) {
            System.out.println("No se encontró el alumno.");
            return;
        }

        mostrarCarreras();
        int num = leerEntero("Número de carrera destino: ");
        System.out.println(gestor.cambiarCarrera(matricula, num));
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}