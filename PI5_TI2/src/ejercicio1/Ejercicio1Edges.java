package ejercicio1;


import datos.DatosEjercicio1;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio1Edges(Ejercicio1Vertex source,Ejercicio1Vertex target,Integer action,Double weight) implements SimpleEdgeAction<Ejercicio1Vertex, Integer> {
	
	public static Ejercicio1Edges of(Ejercicio1Vertex source,Ejercicio1Vertex target,Integer action) {
		Double weight=0.0;
		if(action !=DatosEjercicio1.getNumHuertos()) {
			weight=1.0;
		}
		Ejercicio1Edges a= new Ejercicio1Edges(source, target, action, weight);
		return a;
	}
}
