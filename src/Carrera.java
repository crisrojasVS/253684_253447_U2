import java.util.ArrayList;

public class Carrera {
    private String nombre;
    private ArrayList<Alumno> alumnos = new ArrayList<>();

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    // Para dar de Alta a un Alumno
    public void alta(Alumno alumno) {
        alumnos.add(alumno);
    }

    // Para dar de Baja a un Alumno
    public Alumno baja(String matricula) {
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getMatricula().equals(matricula)) {
                return alumnos.remove(i);
            }
        }
        return null;
    }

    // para la Consulta
    public Alumno buscar(String matricula) {
        for (Alumno a : alumnos) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    public void mostrarAlumnos() {
        if (alumnos.isEmpty()) {
            System.out.println("   (sin alumnos)");
        }
        for (Alumno a : alumnos) {
            System.out.println("   - " + a);
        }
    }
}