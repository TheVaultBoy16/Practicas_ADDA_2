package ejercicio1Manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio1;
import ejercicio1.Ejercicio1Heuristic;
import ejercicio1.Ejercicio1Solucion;
import ejercicio1.Ejercicio1Vertex;


public class Ejercicio1PDR {
	
	public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a,Integer weight) {
			return new Spm(a,weight);
		}

		@Override
		public int compareTo(Spm o) {
			return this.weight.compareTo(o.weight);
		}
	
	}

public static Map<Ejercicio1Vertex,Spm> memory;
public static Integer mejorValor=Integer.MIN_VALUE;//estamos maximizando

public static Ejercicio1Solucion search() {
	memory= new HashMap<>();
	mejorValor=Integer.MIN_VALUE;//estamos maximizando
	Ejercicio1Vertex inicial=Ejercicio1Vertex.first();
	Integer valorAcumulado=0;
	pdr_search(inicial,valorAcumulado,memory);
	
	return getSolucion();
}

private static Ejercicio1Solucion getSolucion() {
	List<Integer> acciones= new ArrayList<Integer>();
	Ejercicio1Vertex actual=Ejercicio1Vertex.first();
	Spm spm=memory.get(actual);
	while(spm!=null&& spm.a()!=null) {
		Ejercicio1Vertex old=actual;
		acciones.add(spm.a());
		actual=old.neighbor(spm.a());
		spm=memory.get(actual);
	}
	return Ejercicio1Solucion.create(acciones);
}

private static Spm pdr_search(Ejercicio1Vertex actual, Integer valorAcumulado, Map<Ejercicio1Vertex, Spm> memory) {
	Spm res=null;
	if(memory.containsKey(actual)) {
		res=memory.get(actual);
	}else {
		if(Ejercicio1Vertex.goal().test(actual)&& Ejercicio1Vertex.goalHasSolution().test(actual)) {
			res=Spm.of(null, 0);
			memory.put(actual, res);
			if(valorAcumulado > mejorValor) {//estamos maximizando
				mejorValor = valorAcumulado;
			}
		}else {
			List<Spm> soluciones=new ArrayList<>();
			for(Integer action:actual.actions()) {
				Double cota=funcionCota(valorAcumulado,actual,action);
				if(cota<=mejorValor) {//estamos minimizando
					continue;
				}
				Ejercicio1Vertex vecino=actual.neighbor(action);
				Integer weight=0;
				if(action!=DatosEjercicio1.getNumHuertos()) {
					weight=1;
				}
				Spm r=pdr_search(vecino, valorAcumulado+weight,memory);
				if( r!=null) {
					Spm combinado=Spm.of(action, r.weight()+weight);
					soluciones.add(combinado);
				}
			}
			res=soluciones.stream().max(Comparator.naturalOrder()).orElse(null);
			if(res!=null) {
				memory.put(actual, res);
			}
		}
	}
	return res;
}

private static Double funcionCota(Integer valorAcumulado, Ejercicio1Vertex actual,Integer action) {
	Integer weight=0;
	if(action!=DatosEjercicio1.getNumHuertos()) {
		weight=1;
	}
	return valorAcumulado+weight+Ejercicio1Heuristic.heuristic(actual.neighbor(action), null, null);
}

}
