package ejercicio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio1;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio1Vertex(Integer indice,List<Set<Integer>> reparto,List<Integer> metrosDisponibles) implements VirtualVertex<Ejercicio1Vertex, Ejercicio1Edges, Integer> {

	
	public static Integer n= DatosEjercicio1.getNumVariedades();
	public static Integer m= DatosEjercicio1.getNumHuertos();
	public static List<Integer> listaMetrosDisponibles=DatosEjercicio1.getListaDeMetrosDisponibles();
	public static List<Set<Integer>> sets=DatosEjercicio1.sets();
	
	
	
	public static Ejercicio1Vertex of(Integer indice,List<Set<Integer>> reparto,List<Integer> metrosDisponibles) {
		return new Ejercicio1Vertex(indice, reparto, metrosDisponibles);
	}
	
	public static Ejercicio1Vertex first() {
		return Ejercicio1Vertex.of(0, new ArrayList<Set<Integer>>(sets),new ArrayList<Integer>(listaMetrosDisponibles));
	}
	
	public static Ejercicio1Vertex last() {
		return Ejercicio1Vertex.of(n, new ArrayList<Set<Integer>>(), new ArrayList<Integer>());
	}
	
	public static Predicate<Ejercicio1Vertex> goal(){
		return v->v.indice()==n;
	}
	
	public static Predicate<Ejercicio1Vertex> goalHasSolution(){
		return v-> true;
	}
	
	@Override
	public Boolean isValid() {
		return indice>=0 && indice<=n;
	}
	
	public Boolean cabeEnHuerto(Integer h) {
		Boolean res=false;
		if(metrosDisponibles.get(h)-DatosEjercicio1.getMetrosRequeridos(indice)>=0) {
			res=true;
		}
		return res;
	}
	
	public Boolean esCompatibleEnHuerto(Set<Integer> rep) {
		Boolean res=true;
		for(Integer k:rep) {
			if(DatosEjercicio1.getIncompatibilidad(indice, k)==1) {
				res=false;
			}
		}
		return res;
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> acciones= new ArrayList<Integer>();
		if(indice<n) {
			acciones.add(m);
				for(int h=0;h<m;h++) {
					if(cabeEnHuerto(h) && esCompatibleEnHuerto(reparto.get(h))) {
						acciones.add(h);
					}
				}				
		}
		else {
			acciones= new ArrayList<Integer>();
		}
		Collections.sort(acciones,Comparator.naturalOrder());
		return acciones;
	}

	@Override
	public Ejercicio1Vertex neighbor(Integer a) {
		List<Integer> listaMetros= new ArrayList<Integer>(metrosDisponibles);
		List<Set<Integer>> listaReparto= new ArrayList<Set<Integer>>(reparto);
			if(a!=m) {
				Set<Integer> c= new HashSet<Integer>(listaReparto.get(a)); 
				Integer x=listaMetros.get(a)-DatosEjercicio1.getMetrosRequeridos(indice);
				c.add(indice);
				listaMetros.set(a, x);
				listaReparto.set(a, c);
		}
		return Ejercicio1Vertex.of(indice+1, listaReparto, listaMetros);
	}

	@Override
	public Ejercicio1Edges edge(Integer a) {
		return Ejercicio1Edges.of(this, this.neighbor(a), a);
	}
	
	public static Ejercicio1Solucion getSolucion(GraphPath<Ejercicio1Vertex, Ejercicio1Edges> path) {
		return Ejercicio1Vertex.getSolucion(path.getEdgeList());
	}
	
	public static Ejercicio1Solucion getSolucion(List<Ejercicio1Edges> ls) {

		List<Integer> alternativas = List2.empty();

		for (Ejercicio1Edges alternativa : ls) {
			alternativas.add(alternativa.action());
		}

		Ejercicio1Solucion s = Ejercicio1Solucion.create(alternativas);

		return s;
	}

}
