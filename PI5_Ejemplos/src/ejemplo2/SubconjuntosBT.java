package ejemplo2;

import soluciones.SolucionSubconjuntos;

public class SubconjuntosBT {

	private static Double mejorValor;
	private static SubconjuntosState estado;
	private static SolucionSubconjuntos solucion;
	
	public static void search() {
		solucion = null;
		mejorValor = Double.MAX_VALUE; // Estamos minimizando
		estado = SubconjuntosState.initial();
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

	public static SolucionSubconjuntos getSolucion() {
		return solucion;
	}

}
