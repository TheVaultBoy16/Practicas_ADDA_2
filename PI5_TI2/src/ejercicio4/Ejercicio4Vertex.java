package ejercicio4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio4;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio4Vertex(Integer indice,Set<Integer> restante,Integer ultima) implements VirtualVertex<Ejercicio4Vertex, Ejercicio4Edges, Integer> {
	
	public static Integer n= DatosEjercicio4.getNumPersonas();
	public static Set<Integer> personasSet=DatosEjercicio4.personasSet();

	public static Ejercicio4Vertex of(Integer indice,Set<Integer> restante,Integer ultima) {
		return new Ejercicio4Vertex(indice, restante, ultima);
	}
	
	public static Ejercicio4Vertex first() {
		return Ejercicio4Vertex.of(0, new HashSet<Integer>(personasSet),n);
	}
	
	public static Ejercicio4Vertex last() {
		return Ejercicio4Vertex.of(n, new HashSet<Integer>(), 0);
	}
	
	public static Predicate<Ejercicio4Vertex> goal(){
		return v->v.indice()==n;
	}
	
	public static Predicate<Ejercicio4Vertex> goalHasSolution(){
		return v->v.restante().size()==0;
	}
	
	@Override
	public Boolean isValid() {
		return indice>=0 && indice<=n;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> acciones= new ArrayList<Integer>();
		if(indice<n) {
				if(indice%2==1) {
					for(Integer j:restante) {
						if(DatosEjercicio4.tienenIdiomaComun(ultima,j) && DatosEjercicio4.diferenciaEdad(ultima,j) && DatosEjercicio4.distintaNacionalidad(ultima,j)) {
							acciones.add(j);
						}
					}

				}else {
					acciones.add(restante.stream().findFirst().get());
				}
		}
		return acciones;
	}

	@Override
	public Ejercicio4Vertex neighbor(Integer a) {
		Integer ult=ultima;
		Set<Integer> rest= new HashSet<Integer>(restante);
		if(indice%2==0) {
			ult=a;
			rest.remove(a);
		}
		else {
			ult=a;
			rest.remove(ult);
		}
		return Ejercicio4Vertex.of(indice+1, rest, ult);
	}

	@Override
	public Ejercicio4Edges edge(Integer a) {
		return Ejercicio4Edges.of(this, this.neighbor(a), a);
	}
	
	public static Ejercicio4Solucion getSolucion(GraphPath<Ejercicio4Vertex, Ejercicio4Edges> path) {
		return Ejercicio4Vertex.getSolucion(path.getEdgeList());
	}

	public static Ejercicio4Solucion getSolucion(List<Ejercicio4Edges> ls) {

		List<Integer> alternativas = List2.empty();

		for (Ejercicio4Edges alternativa : ls) {
			alternativas.add(alternativa.action());
		}

		Ejercicio4Solucion s = Ejercicio4Solucion.create(alternativas);

		return s;
	}

}
