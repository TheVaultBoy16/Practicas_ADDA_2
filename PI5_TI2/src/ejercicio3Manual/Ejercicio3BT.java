package ejercicio3Manual;

import ejercicio3.Ejercicio3Solucion;

public class Ejercicio3BT {
	
	private static Double mejorValor;
	private static Ejercicio3State estado;
	private static Ejercicio3Solucion solucion;
	
	public static void search() {
		solucion = null;
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		estado = Ejercicio3State.initial();
		bt_search();
	}

	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido < mejorValor) {  // Estamos minimizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else if(!estado.esTerminal()){//i!=n
			for (Integer a: estado.alternativas()) {
//				if (estado.cota(a) <= mejorValor) {  // Estamos minimizando
				if (estado.cota(a) < mejorValor) {  // Estamos minimizando
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}

	public static Ejercicio3Solucion getSolucion() {
		return solucion;
	}

}
