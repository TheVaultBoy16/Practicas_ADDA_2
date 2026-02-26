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
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestBT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatosMulticonjunto.iniDatos("ficheros/multiconjuntos.txt",0);
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x->x.weight())
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution()).build();// pathtype sum porque la FO se va calculando sumando los caminos, type.min pa minimisar
		
		BT<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> bt=BT.of(graph,null,null,null,true);
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino= bt.search();
		List<Integer> gp_as=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		//SolucionMulticonjunto.of_Range(camino.get().getEdgeList()); // coger lista de aristas y quedarse con las acciones y quedarse con nose que de algoritm
		SolucionMulticonjunto s=SolucionMulticonjunto.of_Range(gp_as);
		System.out.println(s);
		
		System.out.println(camino.get());
		
		GraphColors.toDot(bt.outGraph(), "ficheros/multiconjuntoBTGraph1.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
		
		// Con heuristica
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph2= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution()).heuristic(MulticonjuntoHeuristic::heuristic).build();
		
		BT<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> bt2=BT.of(graph2,null,null,null,true);
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino2= bt2.search();
//		List<Integer> gp_as2=camino2.get().getEdgeList().stream().map(x2->x2.action()).collect(Collectors.toList());
//		SolucionMulticonjunto s2=SolucionMulticonjunto.of_Range(gp_as2);
//		System.out.println(s2);
		List<Integer> gp_as2=camino2.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		SolucionMulticonjunto s2=SolucionMulticonjunto.of_Range(gp_as2);
		System.out.println(s2);
		
		GraphColors.toDot(bt2.outGraph(), "ficheros/multiconjuntoBTGraph1h.gv",
				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, bt2.optimalPath.getEdgeList().contains(e)));
		
		//CON HEURISTICA Y VORAZ
		
		EGraph<MulticonjuntoVertex, MulticonjuntoEdge> graph3= EGraph.virtual(MulticonjuntoVertex.first(),
				MulticonjuntoVertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.greedyEdge(MulticonjuntoVertex::greedyEdge)
				.goalHasSolution(MulticonjuntoVertex.goalHasSolution())
				.heuristic(MulticonjuntoHeuristic::heuristic).build();
		
//		BT<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> bt3=BT.of(graph3,null,null,null,true);
//		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino3= bt3.search();
//		List<Integer> gp_as3=camino3.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
//		SolucionMulticonjunto s3=SolucionMulticonjunto.of_Range(gp_as3);
//		System.out.println(s3);
		
//		GraphColors.toDot(bt3.outGraph(), "ficheros/multiconjuntoBTGraph1gh.gv",
//				v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red, bt3.optimalPath.getEdgeList().contains(e)));
		
		GreedyOnGraph<MulticonjuntoVertex, MulticonjuntoEdge> rr=GreedyOnGraph.of(graph3);
		
		GraphPath<MulticonjuntoVertex,MulticonjuntoEdge> r=rr.path();
		
		System.out.println("Voraz= "+r.getWeight()+" == "+MulticonjuntoVertex.getSolucion(r));
		
		BT<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> bt3=
				BT.of(graph3,MulticonjuntoVertex::getSolucion,null,null,true);
		
		if(rr.isSolution(r)) {
			bt3=BT.of(graph3,MulticonjuntoVertex::getSolucion,r.getWeight(),r,true);
		}
		
		BT<MulticonjuntoVertex, MulticonjuntoEdge,SolucionMulticonjunto> bt3f=bt3;
		
		Optional<GraphPath<MulticonjuntoVertex, MulticonjuntoEdge>> camino3= bt3f.search();
		
		System.out.println(MulticonjuntoVertex.getSolucion(camino3.get()));
		
		GraphColors.toDot(bt3f.outGraph(), "ficheros/multiconjuntoBTGraph1gh.gv",
		v->String.format("(%d,%d)", v.indice(),v.sumaRestante()),
		e-> e.action().toString(),
		v-> GraphColors.colorIf(Color.red, MulticonjuntoVertex.goal().test(v)),
		e-> GraphColors.colorIf(Color.red, bt3f.optimalPath.getEdgeList().contains(e)));// sale/deberia salir grafo vacio bruh
		
		
		
		
	}

}
