package tests;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import datos.DatosMulticonjunto;
import ejemplo1.MulticonjuntoEdge;
import ejemplo1.MulticonjuntoHeuristic;
import ejemplo1.MulticonjuntoVertex;
import soluciones.SolucionMulticonjunto;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestAStar {

	public static void main(String[] args) {
		DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt",0);
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x->x.weight())
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution()).build();
		
		AStar<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> astar=AStar.of(graph);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino= astar.search();
		List<Integer> gp_as=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionMulticonjunto s=SolucionMulticonjunto.of_Range(gp_as);
		System.out.println(s);
		
		System.out.println(camino.get());
		
		GraphColors.toDot(astar.outGraph(), "ficheros/multiconjuntoBTGraph1astar.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, camino.get().getEdgeList().contains(e)));
		
		//HEURISTICA Y VORAZ
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph2= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.greedyEdge(MulticonjuntoVertex::greedyEdge)
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
		
		AStar<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> astar2=AStar.ofGreedy(graph2);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino2= astar2.search();
		List<Integer> gp_as2=camino2.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionMulticonjunto s2=SolucionMulticonjunto.of_Range(gp_as2);
		System.out.println(s2);
		
		System.out.println(camino2.get());
		
		GraphColors.toDot(astar2.outGraph(), "ficheros/multiconjuntoBTGraph1astargreed.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, camino2.get().getEdgeList().contains(e)));

	}

}
