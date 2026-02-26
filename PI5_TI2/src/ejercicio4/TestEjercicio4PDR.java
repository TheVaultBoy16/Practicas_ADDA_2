package ejercicio4;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio4;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.alg.PDR;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio4PDR {

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
				
				PDR <Ejercicio4Vertex, Ejercicio4Edges,Ejercicio4Solucion> pdr=PDR.of(graph, null, null, null, true);
				
				Optional<GraphPath<Ejercicio4Vertex, Ejercicio4Edges>> camino= pdr.search();
				List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
				Ejercicio4Solucion s=Ejercicio4Solucion.create(ls);
				System.out.println("\nSolucion con heuristica:");
				System.out.println("\n"+s);
				
//				System.out.println(camino.get());
				
				//Descomentar y comentar para probar con los distintos ficheros
				
				GraphColors.toDot(pdr.outGraph(), "ficherosGrafos/Ejercicio4_1_H_PDR.gv",
						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
						e-> e.action().toString(),
						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
						e-> GraphColors.colorIf(Color.red, pdr.optimalPath.getEdgeList().contains(e)));
				
//				GraphColors.toDot(pdr.outGraph(), "ficherosGrafos/Ejercicio4_2_H_PDR.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red, pdr.optimalPath.getEdgeList().contains(e)));
//				
//				GraphColors.toDot(pdr.outGraph(), "ficherosGrafos/Ejercicio4_3_H_PDR.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red, pdr.optimalPath.getEdgeList().contains(e)));
				
			//Solución con heurística y greedy
				
				EGraph<Ejercicio4Vertex, Ejercicio4Edges> graph2= EGraph.virtual(Ejercicio4Vertex.first(),
						Ejercicio4Vertex.goal(),
						PathType.Sum,
						Type.Max)
						.edgeWeight(x->x.weight())
						.goalHasSolution(Ejercicio4Vertex.goalHasSolution()).heuristic(Ejercicio4Heuristic::heuristic).build();
				
				GreedyOnGraph<Ejercicio4Vertex, Ejercicio4Edges> rr=GreedyOnGraph.of(graph2);
				
				GraphPath<Ejercicio4Vertex,Ejercicio4Edges> r=rr.path();
				
				System.out.println("\nSolucion con heuristica y greedy:");
				System.out.println("Voraz= "+r.getWeight()+" == "+Ejercicio4Vertex.getSolucion(r));
				PDR<Ejercicio4Vertex, Ejercicio4Edges,Ejercicio4Solucion> pdr2=
						PDR.of(graph2,Ejercicio4Vertex::getSolucion,null,null,true);
				
				if(rr.isSolution(r)) {
					pdr2=PDR.of(graph2,Ejercicio4Vertex::getSolucion,r.getWeight(),r,true);
				}
				
				PDR<Ejercicio4Vertex, Ejercicio4Edges,Ejercicio4Solucion> pdr2f=
						PDR.of(graph2,Ejercicio4Vertex::getSolucion,null,null,true);
				
				Optional<GraphPath<Ejercicio4Vertex, Ejercicio4Edges>> camino2= pdr2f.search();
				

//				System.out.println("\n"+Ejercicio4Vertex.getSolucion(camino2.get()));
				
				//Descomentar y comentar para probar con los distintos ficheros
				
				GraphColors.toDot(pdr2f.outGraph(), "ficherosGrafos/Ejercicio4_1_H&G_PDR.gv",
						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
						e-> e.action().toString(),
						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
						e-> GraphColors.colorIf(Color.red, pdr2f.optimalPath.getEdgeList().contains(e)));
				
//				GraphColors.toDot(pdr2f.outGraph(), "ficherosGrafos/Ejercicio4_2_H&G_PDR.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red, pdr2f.optimalPath.getEdgeList().contains(e)));
//				
//				GraphColors.toDot(pdr2f.outGraph(), "ficherosGrafos/Ejercicio4_3_H&G_PDR.gv",
//						v->String.format("(%s,%s,%s)", v.indice().toString(),v.restante().toString(),v.ultima().toString()),
//						e-> e.action().toString(),
//						v-> GraphColors.colorIf(Color.red, Ejercicio4Vertex.goal().test(v)),
//						e-> GraphColors.colorIf(Color.red, pdr2f.optimalPath.getEdgeList().contains(e)));

	}

}
