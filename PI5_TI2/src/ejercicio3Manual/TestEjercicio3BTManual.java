package ejercicio3Manual;

import datos.DatosEjercicio3;

public class TestEjercicio3BTManual {

	public static void main(String[] args) {
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
		Ejercicio3BT.search();
		System.out.println(Ejercicio3BT.getSolucion()+ "\n");
		
//		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
//		Ejercicio3BT.search();
//		System.out.println(Ejercicio3BT.getSolucion()+ "\n");
//		
//		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
//		Ejercicio3BT.search();
//		System.out.println(Ejercicio3BT.getSolucion()+ "\n");

	}

}
