package ejercicio4;


import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import datos.DatosEjercicio4;

public class Ejercicio4Heuristic {
	
	public static Double heuristic(Ejercicio4Vertex v1,Predicate<Ejercicio4Vertex> goal,Ejercicio4Vertex v2) {
		Double h=0.0;
		if(v1.indice()%2==0) {
			h=IntStream.range(v1.indice(), DatosEjercicio4.getNumPersonas()).mapToDouble(i->Collections.max(DatosEjercicio4.getListaAfinidades(i))).max().orElse(0.0);
		}
		return h;
		
	}
}

