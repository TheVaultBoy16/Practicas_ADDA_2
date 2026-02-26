package ejercicio2;



import datos.DatosEjercicio2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio2Edges(Ejercicio2Vertex source,Ejercicio2Vertex target,Integer action,Double weight) implements SimpleEdgeAction<Ejercicio2Vertex, Integer> {
	
	public static Ejercicio2Edges of(Ejercicio2Vertex source,Ejercicio2Vertex target,Integer action) {
		Double weight=action*DatosEjercicio2.getPrecio(source.indice()).doubleValue();
		Ejercicio2Edges a= new Ejercicio2Edges(source, target, action, weight);
		return a;
	}
}
