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
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestPDR {

	public static void main(String[] args) {
		DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt",0);
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x->x.weight())
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution()).build();
		
		PDR<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> pdr=PDR.of(graph,null,null,null,true);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino= pdr.search();
		List<Integer> gp_as=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionMulticonjunto s=SolucionMulticonjunto.of_Range(gp_as);
		System.out.println(s);
		
		System.out.println(camino.get());
		
		GraphColors.toDot(pdr.outGraph(), "ficheros/multiconjuntoBTGraph1pdr.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, pdr.optimalPath.getEdgeList().contains(e)));
		
		//HEURISTICA
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph2= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
		
		PDR<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> pdr2=PDR.of(graph2,null,null,null,true);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino2= pdr2.search();
		List<Integer> gp_as2=camino2.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionMulticonjunto s2=SolucionMulticonjunto.of_Range(gp_as2);
		System.out.println(s2);
		
		System.out.println(camino2.get());
		
		GraphColors.toDot(pdr2.outGraph(), "ficheros/multiconjuntoBTGraph1hpdr.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, pdr2.optimalPath.getEdgeList().contains(e)));
		
		//HEURISTICA Y VORAZ
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph3= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.greedyEdge(MulticonjuntoVertex::greedyEdge)
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
		
		GreedyOnGraph<MulticonjuntoVertex, MulticonjuntoEdge> rr=GreedyOnGraph.of(graph3);
		
		GraphPath<MulticonjuntoVertex,MulticonjuntoEdge> r=rr.path();
		
		System.out.println("Voraz= "+r.getWeight()+" == "+MulticonjuntoVertex.getSolucion(r));
		
		PDR<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> pdr3=
				PDR.of(graph3,MulticonjuntoVertex::getSolucion,null,null,true);
		
		if(rr.isSolution(r)) {
			pdr3=PDR.of(graph3,MulticonjuntoVertex::getSolucion,r.getWeight(),r,true);
		}
		
		PDR<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> pdr3f=
				PDR.of(graph3,MulticonjuntoVertex::getSolucion,null,null,true);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino3= pdr3f.search();
		
		System.out.println(MulticonjuntoVertex.getSolucion(camino3.get()));
		
		GraphColors.toDot(pdr3f.outGraph(), "ficheros/multiconjuntoBTGraph1ghpdr.gv",
		v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
		e-> e.action().toString(),
		v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
		e-> GraphColors.colorIf(Color.red, pdr3f.optimalPath.getEdgeList().contains(e)));

	}

}
