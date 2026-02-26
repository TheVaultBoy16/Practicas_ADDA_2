package ejercicio2Manual;

import datos.DatosEjercicio2;
import ejercicio2.Ejercicio2Solucion;

public class TestEjercicio2PDRManual {

	public static void main(String[] args) {
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		Ejercicio2Solucion sol=Ejercicio2PDR.search();
		System.out.println(sol);
		
//		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada2.txt");
//		Ejercicio2Solucion sol=Ejercicio2PDR.search();
//		System.out.println(sol);
//		
//		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
//		Ejercicio2Solucion sol=Ejercicio2PDR.search();
//		System.out.println(sol);

	}

}
