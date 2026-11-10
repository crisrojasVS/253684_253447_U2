import java.util.ArrayList;

public class GestorEscolar {
    private ArrayList<Carrera> carreras = new ArrayList<>();

    public GestorEscolar() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Carrera sistemas = new Carrera("Ingeniería en Sistemas");
        Carrera industrial = new Carrera("Ingeniería Industrial");
        Carrera mecatronica = new Carrera("Mecatrónica");

        sistemas.alta(new Alumno("Ana López", "A001", 3));
        sistemas.alta(new Alumno("Luis Pérez", "A002", 5));
        industrial.alta(new Alumno("María Gómez", "A003", 2));
        mecatronica.alta(new Alumno("Carlos Ruiz", "A004", 4));

        carreras.add(sistemas);
        carreras.add(industrial);
        carreras.add(mecatronica);
    }

    public ArrayList<Carrera> getCarreras() {
        return carreras;
    }

    // Devuelve la carrera donde está el alumno (null si no existe)
    public Carrera buscarCarreraDeAlumno(String matricula) {
        for (Carrera c : carreras) {
            if (c.buscar(matricula) != null) {
                return c;
            }
        }
        return null;
    }

    // Devuelve el alumno (null si no existe)
    public Alumno buscarAlumno(String matricula) {
        Carrera c = buscarCarreraDeAlumno(matricula);
        if (c == null) {
            return null;
        }
        return c.buscar(matricula);
    }

    public String agregarAlumno(String nombre, String matricula,
                                int cuatrimestre, int numCarrera) {
        if (buscarAlumno(matricula) != null) {
            return "Esa matrícula ya está registrada.";
        }
        if (cuatrimestre < 1) {
            return "Cuatrimestre no válido.";
        }
        if (numCarrera < 1 || numCarrera > carreras.size()) {
            return "Carrera no válida.";
        }

        Carrera carrera = carreras.get(numCarrera - 1);
        carrera.alta(new Alumno(nombre, matricula, cuatrimestre));
        return "Alumno agregado en " + carrera.getNombre();
    }

    public String cambiarCarrera(String matricula, int numDestino) {
        Carrera origen = buscarCarreraDeAlumno(matricula);
        if (origen == null) {
            return "No se encontró el alumno.";
        }
        if (numDestino < 1 || numDestino > carreras.size()) {
            return "Carrera no válida.";
        }

        Carrera destino = carreras.get(numDestino - 1);
        if (destino == origen) {
            return "El alumno ya pertenece a esa carrera.";
        }

        // Baja en origen y alta en destino
        Alumno alumno = origen.baja(matricula);
        destino.alta(alumno);

        return "Cambio realizado: baja en " + origen.getNombre()
                + " y alta en " + destino.getNombre();
    }
}