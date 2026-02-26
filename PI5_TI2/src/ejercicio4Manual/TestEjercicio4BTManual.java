package ejercicio4Manual;

import datos.DatosEjercicio4;
import ejercicio4.Ejercicio4Solucion;

public class TestEjercicio4BTManual {

	public static void main(String[] args) {
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
		Ejercicio4BT.search();
		Ejercicio4Solucion sol=Ejercicio4BT.getSolucion();
		System.out.println(sol);
		
//		DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada2.txt");
//		Ejercicio4BT.search();
//		Ejercicio4Solucion sol=Ejercicio4BT.getSolucion();
//		System.out.println(sol);
//		
//		DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada3.txt");
//		Ejercicio4BT.search();
//		Ejercicio4Solucion sol=Ejercicio4BT.getSolucion();
//		System.out.println(sol);

	}

}
