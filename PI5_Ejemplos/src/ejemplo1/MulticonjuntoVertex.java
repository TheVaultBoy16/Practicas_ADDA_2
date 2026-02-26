package ejemplo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import datos.DatosMulticonjunto;
import soluciones.SolucionMulticonjunto;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;



public record MulticonjuntoVertex(Integer indice,Integer sumaRestante) implements VirtualVertex<MulticonjuntoVertex, MulticonjuntoEdge, Integer> {

	
	public static Integer n=DatosMulticonjunto.getNumElementos();
	
	public static MulticonjuntoVertex of(Integer indice,Integer sumaRestante) {
		return new MulticonjuntoVertex(indice, sumaRestante);
	}
	
	public static MulticonjuntoVertex first() {
		return MulticonjuntoVertex.of(0, DatosMulticonjunto.getSuma());
	}
	
	public static MulticonjuntoVertex last() {// hay veces que no hace falta
		return MulticonjuntoVertex.of(n,0);// todos asi siempre
	}
	public static Predicate<MulticonjuntoVertex> goal(){// comprueba si el indice del vertex llega al n
		return v->v.indice() ==n;
	}
	
	@Override // pero este no viene por defecto
	public Boolean isValid() {// para ver si un vertice es valido
		return indice>=0 && indice<=n && sumaRestante>=0;// compruebo rango de propiedades individuales
	}
	
	public static Predicate<MulticonjuntoVertex> goalHasSolution(){// compruebo si el objetivo es solucion, cuando llega a la N si es solucion o no
		return v-> v.sumaRestante()==0;// compruebo si se ha llegado justo al numero de la suma objetivo
	}
	
//	public static SolucionMulticonjunto getSolucion(GraphPath<MulticonjuntoVertex, MulticonjuntoEdge> path) {
//		return MulticonjuntoVertex.getSolucion(path.getEdgeList());
//	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> acciones= new ArrayList<Integer>();
		
		if(indice<n) {
			if(indice == n-1) {// ultimo elemento de la lista de los datos
				if(sumaRestante %DatosMulticonjunto.getElemento(indice)==0) {//compruebo si con ese valor llego a la suma objetivo, no me paso
					Integer maxDivisor=sumaRestante/DatosMulticonjunto.getElemento(indice);
					acciones.add(maxDivisor);// pillar por huevos este para cumplir no?? por eso en la ultima no se puede no pillar nada pues se tiene que llegar a la solucion
				}
			}
			else {// cualquier elemento intermedio de la lista
				Integer maxDivisor=sumaRestante/DatosMulticonjunto.getElemento(indice);// miro lo maximo que puedo añadir segun lo que quede de suma
				acciones=IntStream.rangeClosed(0, maxDivisor).boxed().toList();// boxed para pasarlo a stream
//				Collections.sort(acciones,Collections.reverseOrder());// lo de antes poner al reves el orden de las acciones, en cada iteracion pillar el moyor numero de veces el mas grande asi no funciona
				acciones=List2.reverse(acciones);// usar esto que no modifique la lista sino que la ordene y la devuelva
			}	
		}
		return acciones;
	}

	@Override
	public MulticonjuntoVertex neighbor(Integer a) {
		return MulticonjuntoVertex.of(indice+1, sumaRestante-a*DatosMulticonjunto.getElemento(indice));// lo que va en la alternativa por el numero lo del papel, siendo a el numero de veces que lo pillo 
	}

	@Override
	public MulticonjuntoEdge edge(Integer a) {
		return MulticonjuntoEdge.of(this,this.neighbor(a),a);// this llama al record este es la propia clase el otro this. no es necesario
	}
	
	public static SolucionMulticonjunto getSolucion(GraphPath<MulticonjuntoVertex, MulticonjuntoEdge> path) {
		return MulticonjuntoVertex.getSolucion(path.getEdgeList());
	}

	public static SolucionMulticonjunto getSolucion(List<MulticonjuntoEdge> ls) {

		List<Integer> alternativas = List2.empty();

		for (MulticonjuntoEdge alternativa : ls) {
			alternativas.add(alternativa.action());
		}

		SolucionMulticonjunto s = SolucionMulticonjunto.of_Range(alternativas);

		return s;
	}
	
	// para el voraz
	
	public MulticonjuntoEdge greedyEdge() {
		return edge(accionEntera());
	}

	public Integer accionEntera() {
		return this.sumaRestante() / DatosMulticonjunto.getElemento(this.indice);// en el voraz solo pillo 1
	}

}
