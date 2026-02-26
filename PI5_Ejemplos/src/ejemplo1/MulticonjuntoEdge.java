package ejemplo1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record MulticonjuntoEdge(MulticonjuntoVertex source,MulticonjuntoVertex target,Integer action,Double weight) implements SimpleEdgeAction<MulticonjuntoVertex, Integer> {
	//peso por defecto siempre double
	
	public static MulticonjuntoEdge of(MulticonjuntoVertex source,MulticonjuntoVertex target,Integer action) {
		Double weight=action*1.0;// para pasarlo a double *1.0
		MulticonjuntoEdge a= new MulticonjuntoEdge(source, target, action, weight);
		return a;
	}
}
