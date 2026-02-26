package ejercicio1Manual;

import datos.DatosEjercicio1;
import ejercicio1.Ejercicio1Solucion;


public class TestEjercicio1PDRManual {

	public static void main(String[] args) {
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		Ejercicio1Solucion sol=Ejercicio1PDR.search();
		System.out.println(sol);
		
//		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada2.txt");
//		Ejercicio1Solucion sol=Ejercicio1PDR.search();
//		System.out.println(sol);
//		
//		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
//		Ejercicio1Solucion sol=Ejercicio1PDR.search();
//		System.out.println(sol);

	}

}
