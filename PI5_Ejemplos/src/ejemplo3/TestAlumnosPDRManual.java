package ejemplo3;

public class TestAlumnosPDRManual {

	public static void main(String[] args) {
		DatosAlumnos.iniDatos("ficheros/alumnos_1.txt");
		SolucionAlumnos sol=AlumnosPDR.search();
		System.out.println(sol);
		

	}

}
