public class Alumno {
    private String nombre;
    private String matricula;
    private int cuatrimestre;

    public Alumno(String nombre, String matricula, int cuatrimestre) {
        this.nombre = nombre;
        this.matricula = matricula;
        this.cuatrimestre = cuatrimestre;
    }

    public String getNombre() { return nombre; }
    public String getMatricula() { return matricula; }
    public int getCuatrimestre() { return cuatrimestre; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Matrícula: " + matricula
                + " | Cuatrimestre: " + cuatrimestre;
    }
}