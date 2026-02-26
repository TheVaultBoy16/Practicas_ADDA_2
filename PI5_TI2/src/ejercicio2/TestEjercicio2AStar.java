package ejercicio2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio2AStar {

	public static void main(String[] args) {
		
		//Solución con heurística
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
//		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada2.txt");
//		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
		
		EGraph<Ejercicio2Vertex, Ejercicio2Edges> graph= EGraph.virtual(Ejercicio2Vertex.first(),
				Ejercicio2Vertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x->x.weight()) 
				.goalHasSolution(Ejercicio2Vertex.goalHasSolution()).heuristic(Ejercicio2Heuristic::heuristic).build();
		
		AStar <Ejercicio2Vertex, Ejercicio2Edges,Ejercicio2Solucion> astar=AStar.of(graph);

		
		Optional<GraphPath<Ejercicio2Vertex, Ejercicio2Edges>> camino= astar.search();
		List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		Ejercicio2Solucion s=Ejercicio2Solucion.create(ls);
		System.out.println("\nSolucion con heuristica:");
		System.out.println("\n"+s);
		
//		System.out.println(camino.get());
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio2_1_AStar.gv",
				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
		
//		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio2_2_AStar.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
//		
//		GraphColors.toDot(astar.outGraph(), "ficherosGrafos/Ejercicio2_3_AStar.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red,camino.get().getEdgeList().contains(e)));
		
		

	}

}
