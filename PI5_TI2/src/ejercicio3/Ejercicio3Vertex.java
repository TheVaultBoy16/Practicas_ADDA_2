package ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio3;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio3Vertex(Integer z,List<Integer> unidadesRestantes,List<Integer> demandasRestantes) implements VirtualVertex<Ejercicio3Vertex, Ejercicio3Edges, Integer> {
	
	public static Integer n=DatosEjercicio3.getNumProductos();//i
	public static Integer m=DatosEjercicio3.getNumDestinos();//j
	public static List<Integer> listaUnidadesRestantes=DatosEjercicio3.listaUnidadesRestantes();
	public static List<Integer> listaDemandasRestantes=DatosEjercicio3.listaDemandasRestantes();
	
	public static Ejercicio3Vertex of(Integer z,List<Integer> unidadesRestantes,List<Integer> demandasRestantes) {
		return new Ejercicio3Vertex(z, unidadesRestantes, demandasRestantes);
	}
	
	public static Ejercicio3Vertex first() {
		return Ejercicio3Vertex.of(0, new ArrayList<Integer>(listaUnidadesRestantes),new ArrayList<Integer>(listaDemandasRestantes));
	}
	
	public static Ejercicio3Vertex last() {
		return Ejercicio3Vertex.of(n*m, new ArrayList<Integer>(),new ArrayList<Integer>());
	}
	
	public static Predicate<Ejercicio3Vertex> goal(){
		return v->v.z()==n*m;
	}
	
	public static Predicate<Ejercicio3Vertex> goalHasSolution(){
		return v->v.demandasRestantes().stream().allMatch(x->x==0);
	}
	
	@Override
	public Boolean isValid() {
		return  z>=0 && z<=n*m;
	}
	

	@Override
	public List<Integer> actions() {
		List<Integer> acciones = new ArrayList<Integer>();
		if(z< n*m) {
			Integer unidadesRestantesActual=unidadesRestantes.get(z/m);
			Integer demandasRestantesActual=demandasRestantes.get(z%m);
			if(demandasRestantesActual <=0 || unidadesRestantesActual<=0) {
				acciones.add(0);
			}
			else {
				acciones.add(Math.min(unidadesRestantesActual, demandasRestantesActual));
			}
		}
		return acciones;
	}

	@Override
	public Ejercicio3Vertex neighbor(Integer a) {
		List<Integer> ur=new ArrayList<Integer>(unidadesRestantes);
		List<Integer> dr=new ArrayList<Integer>(demandasRestantes);
		Integer i=ur.get(z/m);
		Integer j=dr.get(z%m);
		ur.set(z/m, i-a);
		dr.set(z%m, j-a);
		return Ejercicio3Vertex.of(z+1, ur, dr);
	}

	@Override
	public Ejercicio3Edges edge(Integer a) {
		return Ejercicio3Edges.of(this, this.neighbor(a), a);
	}
	
	public static Ejercicio3Solucion getSolucion(GraphPath<Ejercicio3Vertex, Ejercicio3Edges> path) {
		return Ejercicio3Vertex.getSolucion(path.getEdgeList());
	}

	public static Ejercicio3Solucion getSolucion(List<Ejercicio3Edges> ls) {

		List<Integer> alternativas = List2.empty();

		for (Ejercicio3Edges alternativa : ls) {
			alternativas.add(alternativa.action());
		}

		Ejercicio3Solucion s = Ejercicio3Solucion.create(alternativas);

		return s;
	}

}
