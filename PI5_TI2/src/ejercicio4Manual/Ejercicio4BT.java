package ejercicio4Manual;

import ejercicio4.Ejercicio4Solucion;

public class Ejercicio4BT {
	
	private static Double mejorValor;
	private static Ejercicio4State estado;
	private static Ejercicio4Solucion solucion;
	
	public static void search() {
		solucion = null;
		mejorValor = Double.MIN_VALUE; // Estamos maximizando
		estado = Ejercicio4State.initial();
		bt_search();
	}

	private static void bt_search() {
		if (estado.esTerminal()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido > mejorValor) {  // Estamos maximizando
				mejorValor = valorObtenido;
				solucion = estado.getSolucion();
			}
		} else if(estado.esSolucion()) {//i!=n
			for (Integer a: estado.actual.actions()) {
				if (estado.cota(a) < mejorValor) {// Estamos maximizando
					continue;
				}
					estado.forward(a);
					bt_search();
					estado.back();
			}
		}
	}

	public static Ejercicio4Solucion getSolucion() {
		return solucion;
	}

}
