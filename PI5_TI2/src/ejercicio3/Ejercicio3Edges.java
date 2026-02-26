package ejercicio3;

import datos.DatosEjercicio3;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio3Edges(Ejercicio3Vertex source,Ejercicio3Vertex target,Integer action,Double weight) implements SimpleEdgeAction<Ejercicio3Vertex, Integer> {
	
	public static Ejercicio3Edges of(Ejercicio3Vertex source,Ejercicio3Vertex target,Integer action) {
		Integer i=source.z()/DatosEjercicio3.getNumDestinos();
		Integer j=source.z()%DatosEjercicio3.getNumDestinos();
		Double weight=DatosEjercicio3.getCosteAlmacenamiento(i, j) * action *1.0;
		return new Ejercicio3Edges(source, target, action, weight);
	}
}
