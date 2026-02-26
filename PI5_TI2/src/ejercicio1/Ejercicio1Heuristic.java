package ejercicio1;




import java.util.function.Predicate;

import datos.DatosEjercicio1;


public class Ejercicio1Heuristic {
		
	public static Double heuristic(Ejercicio1Vertex v1, Predicate<Ejercicio1Vertex> goal,Ejercicio1Vertex v2) {
		Double h=0.;
		for (int i=v1.indice();i<DatosEjercicio1.getNumVariedades();i++) {
			for(int hu=0;hu<DatosEjercicio1.getNumHuertos();hu++) {
				Integer metrosRestantes=v1.metrosDisponibles().get(hu)-DatosEjercicio1.getMetrosRequeridos(i);				
				if(metrosRestantes>=0) {
					h+=1.;
					break;
				}
			}
		}
		return h;
	}
}
