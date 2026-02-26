package ejercicio4;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;


import datos.DatosEjercicio4;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio4AStar {

	public static void main(String[] args) {
		
		//Solución con heurística
		
		//Descomentar y comentar para probar con los distintos ficheros
		
				DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
//				DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada2.txt");
//				DatosEjercicio4.iniDatos("ficheros/Ejercicio4DatosEntrada3.txt");
				
				EGraph<Ejercicio4Vertex, Ejercicio4Edges> graph= EGraph.virtual(Ejercicio4Vertex.first(),
						Ejercicio4Vertex.goal(),
						PathType.Sum,
						Type.Max)
						.edgeWeight(x->x.weight())
						.goalHasSolution(Ejercicio4Vertex.goalHasSolution()).heuristic(Ejercicio4Heuristic::heuristic).build();
				
				AStar <Ejercicio4Vertex, Ejercicio4Edges,Ejercicio4Solucion> astar=AStar.of(graph);

				
				Optional<GraphPath<Ejercicio4Vertex, Ejercicio4Edges>> camino= astar.search();
				List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
				Ejercicio4Solucion s=Ejercicio4Solucion.create(ls);
				System.out.println("\nSolucion con heuristica:");
				System.out.println("\n"+s);
				
//				System.out.println(camino.get());
				
				//Descomentar y comentar para probar con los distintos ficheros
				
				GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio4_1_AStar.gv",
						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
						e-> e.action().toString(),
						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
						e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
				
//				GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio4_2_AStar.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
//				
//				GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio4_3_AStar.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));

	}

}
