package ejercicio3;


import java.util.function.Predicate;
import java.util.stream.IntStream;

import datos.DatosEjercicio3;



public class Ejercicio3Heuristic {
	
	public static Double heuristic(Ejercicio3Vertex v1,Predicate<Ejercicio3Vertex> goal,Ejercicio3Vertex v2) {
		Double h=0.;
		if(!v1.demandasRestantes().stream().allMatch(x->x==0)) {
		h=IntStream.range(v1.z(), DatosEjercicio3.getNumDestinos()*DatosEjercicio3.getNumProductos()).  //.filter(i->v1.demandasRestantes().get(i%DatosEjercicio3.getNumDestinos())>0).
				mapToDouble(z->DatosEjercicio3.getCosteAlmacenamiento(z/DatosEjercicio3.getNumDestinos(), z%DatosEjercicio3.getNumDestinos())).min().orElse(0);
	}
		return h;
	}

}
