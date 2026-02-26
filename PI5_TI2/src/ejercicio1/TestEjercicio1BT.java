package ejercicio1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestEjercicio1BT {

	public static void main(String[] args) {
		
		//Solución con heurística
			
		//Descomentar y comentar para probar con los distintos ficheros
		
			DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
//			DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada2.txt");
//			DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
			
			EGraph<Ejercicio1Vertex, Ejercicio1Edges> graph= EGraph.virtual(Ejercicio1Vertex.first(),
					Ejercicio1Vertex.goal(),
					PathType.Sum,
					Type.Max)
					.edgeWeight(x->x.weight())
					.goalHasSolution(Ejercicio1Vertex.goalHasSolution()).heuristic(Ejercicio1Heuristic::heuristic).build();
			BT <Ejercicio1Vertex, Ejercicio1Edges,Ejercicio1Solucion> bt=BT.of(graph, null, null, null, true);
			
			Optional<GraphPath<Ejercicio1Vertex, Ejercicio1Edges>> camino= bt.search();
			
			List<Integer> ls=camino.get().getEdgeList().stream().map(x->x.action()).collect(Collectors.toList());
			System.out.println("\nSolucion con heuristica:");
			Ejercicio1Solucion s=Ejercicio1Solucion.create(ls);
			System.out.println("\n"+s);
			
			//Descomentar y comentar para probar con los distintos ficheros
			
			GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio1_1_H_BT.gv",
					v->String.format("(%s,%s,%s)", v.indice().toString(),v.reparto().toString(),v.metrosDisponibles().toString()),
					e-> e.action().toString(),
					v-> GraphColors.colorIf(Color.red, Ejercicio1Vertex.goal().test(v)),
					e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
			
//			GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio1_2_H_BT.gv",
//					v->String.format("(%s,%s,%s)", v.indice().toString(),v.reparto().toString(),v.metrosDisponibles().toString()),
//					e-> e.action().toString(),
//					v-> GraphColors.colorIf(Color.red, Ejercicio1Vertex.goal().test(v)),
//					e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
//			
//			GraphColors.toDot(bt.outGraph(), "ficherosGrafos/Ejercicio1_3_H_BT.gv",
//					v->String.format("(%s,%s,%s)", v.indice().toString(),v.reparto().toString(),v.metrosDisponibles().toString()),
//					e-> e.action().toString(),
//					v-> GraphColors.colorIf(Color.red, Ejercicio1Vertex.goal().test(v)),
//					e-> GraphColors.colorIf(Color.red, bt.optimalPath.getEdgeList().contains(e)));
		
		
		

	}

}
