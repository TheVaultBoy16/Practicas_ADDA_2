package ejercicio2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio2BT {

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
				.edgeWeight(x2->x2.weight())
				.goalHasSolution(Ejercicio2Vertex.goalHasSolution()).heuristic(Ejercicio2Heuristic::heuristic).build();
		
		BT<Ejercicio2Vertex, Ejercicio2Edges,Ejercicio2Solucion> bt=BT.of(graph,null,null,null,true);
		Optional<GraphPath<Ejercicio2Vertex, Ejercicio2Edges>> camino= bt.search();
		List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
		Ejercicio2Solucion s=Ejercicio2Solucion.create(ls);
		System.out.println("\nSolucion con heuristica:");
		System.out.println("\n"+s);
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio2_1_H_BT.gv",
				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
		
//		GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio2_2_H_BT.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
//		
//		GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio2_3_H_BT.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
		
		
		//Solución con heurística y greedy
		
		
		EGraph<Ejercicio2Vertex, Ejercicio2Edges> graph2= EGraph.virtual(Ejercicio2Vertex.first(),
				Ejercicio2Vertex.goal(),
				PathType.Sum,
				Type.Min)
				.edgeWeight(x2->x2.weight())
				.goalHasSolution(Ejercicio2Vertex.goalHasSolution())
				.heuristic(Ejercicio2Heuristic::heuristic).build();
		
		GreedyOnGraph<Ejercicio2Vertex, Ejercicio2Edges> rr=GreedyOnGraph.of(graph2);
		
		GraphPath<Ejercicio2Vertex,Ejercicio2Edges> r=rr.path();
		
		BT<Ejercicio2Vertex, Ejercicio2Edges,Ejercicio2Solucion> bt2=BT.of(graph2,Ejercicio2Vertex::getSolucion,null,null,true);
		if(rr.isSolution(r)) {
			bt2=BT.of(graph2,Ejercicio2Vertex::getSolucion,r.getWeight(),r,true);
		}
		BT<Ejercicio2Vertex, Ejercicio2Edges,Ejercicio2Solucion> bt2f=bt2;
		
		Optional<GraphPath<Ejercicio2Vertex, Ejercicio2Edges>> camino2= bt2f.search();
		

		System.out.println("\nSolucion con heuristica y greedy:");
		System.out.println("\n"+Ejercicio2Vertex.getSolucion(camino2.get()));
		System.out.println("\nVoraz= "+r.getWeight()+" == "+Ejercicio2Vertex.getSolucion(r));
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		GraphColors.toDot(bt2.outGraph(), "ficherosGrafos/Ejercicio2_1_H&G_BT.gv",
				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
				e-> e.action().toString(),
				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
				e-> GraphColors.colorIf(Color.red, bt2f.optimalPath.getEdgeList().contains(e)));
		
//		GraphColors.toDot(bt2.outGraph(), "ficherosGrafos/Ejercicio2_2_H&G_BT.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red, bt2f.optimalPath.getEdgeList().contains(e)));
//		
//		GraphColors.toDot(bt2.outGraph(), "ficherosGrafos/Ejercicio2_3_H&G_BT.gv",
//				v->String.format("(%s,%s,%s,%s)", v.indice().toString(),v.categoriasPorCubir().toString(),v.presupuestoRestante().toString(),v.acumValoracion().toString()),
//				e-> e.action().toString(),
//				v-> GraphColors.colorIf(Color.red, Ejercicio2Vertex.goal().test(v)),
//				e-> GraphColors.colorIf(Color.red, bt2f.optimalPath.getEdgeList().contains(e)));
		
	}
	

}
