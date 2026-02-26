package ejercicio4;


import datos.DatosEjercicio4;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio4Edges(Ejercicio4Vertex source,Ejercicio4Vertex target,Integer action,Double weight) implements SimpleEdgeAction<Ejercicio4Vertex, Integer> {
	
	public static Ejercicio4Edges of(Ejercicio4Vertex source,Ejercicio4Vertex target,Integer action) {
		Double weight=0.0;
		if(source.indice()%2==1) {
			weight=DatosEjercicio4.getAfinidadCon(source.ultima(), action)*1.0;
		}
		return new Ejercicio4Edges(source, target, action, weight);
	}

}
