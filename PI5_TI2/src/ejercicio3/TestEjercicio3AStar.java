package ejercicio3;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;


import datos.DatosEjercicio3;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio3AStar {

	public static void main(String[] args) {
		
		//Solución con heuristica
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
//		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
//		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
		
		EGraph<Ejercicio3Vertex, Ejercicio3Edges> graph= EGraph.virtual(Ejercicio3Vertex.first(),
				Ejercicio3Vertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x->x.weight()) 
				.goalHasSolution(Ejercicio3Vertex.goalHasSolution()).heuristic(Ejercicio3Heuristic::heuristic).build();
		
		AStar <Ejercicio3Vertex, Ejercicio3Edges,Ejercicio3Solucion> astar=AStar.of(graph);

		
		Optional<GraphPath<Ejercicio3Vertex, Ejercicio3Edges>> camino= astar.search();
		List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		Ejercicio3Solucion s=Ejercicio3Solucion.create(ls);
		System.out.println("\nSolucion con heuristica:");
		System.out.println("\n"+s);
		
		//Descomentar y comentar para probar con los distintos ficheros
		
//		System.out.println(camino.get());
		
		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio3_1_AStar.gv",
				v->String.format("(%s,%s,%s)", v.z().toString(),v.unidadesRestantes().toString(),v.demandasRestantes().toString()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, Ejercicio3Vertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
		
//		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio3_2_AStar.gv",
//				v->String.format("(%s,%s,%s)", v.z().toString(),v.unidadesRestantes().toString(),v.demandasRestantes().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio3Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
//		
//		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio3_3_AStar.gv",
//				v->String.format("(%s,%s,%s)", v.z().toString(),v.unidadesRestantes().toString(),v.demandasRestantes().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio3Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
	}

}
