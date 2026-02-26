package ejercicio2Manual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio2;
import ejercicio2.Ejercicio2Heuristic;
import ejercicio2.Ejercicio2Solucion;
import ejercicio2.Ejercicio2Vertex;

public class Ejercicio2PDR {
	
	public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {
		
			public static Spm of(Integer a,Integer weight) {
				return new Spm(a,weight);
			}

			@Override
			public int compareTo(Spm o) {
				return this.weight.compareTo(o.weight);
			}
		
	}
	
	public static Map<Ejercicio2Vertex,Spm> memory;
	public static Integer mejorValor=Integer.MAX_VALUE;//estamos minimizando
	
	public static Ejercicio2Solucion search() {
		memory= new HashMap<>();
		mejorValor=Integer.MAX_VALUE;//estamos minimizando
		Ejercicio2Vertex inicial=Ejercicio2Vertex.first();
		Integer valorAcumulado=0;
		pdr_search(inicial,valorAcumulado,memory);
		
		return getSolucion();
	}

	private static Ejercicio2Solucion getSolucion() {
		List<Integer> acciones= new ArrayList<Integer>();
		Ejercicio2Vertex actual=Ejercicio2Vertex.first();
		Spm spm=memory.get(actual);
		while(spm!=null&& spm.a()!=null) {
			Ejercicio2Vertex old=actual;
			acciones.add(spm.a());
			actual=old.neighbor(spm.a());
			spm=memory.get(actual);
		}
		return Ejercicio2Solucion.create(acciones);
	}

	private static Spm pdr_search(Ejercicio2Vertex actual, Integer valorAcumulado, Map<Ejercicio2Vertex, Spm> memory) {
		Spm res=null;
		if(memory.containsKey(actual)) {
			res=memory.get(actual);
		}else {
			if(Ejercicio2Vertex.goal().test(actual)&& Ejercicio2Vertex.goalHasSolution().test(actual)) {
				res=Spm.of(null, 0);
				memory.put(actual, res);
				if(valorAcumulado < mejorValor) {//estamos minimizando
					mejorValor = valorAcumulado;
				}
			}else {
				List<Spm> soluciones=new ArrayList<>();
				for(Integer action:actual.actions()) {
					Double cota=funcionCota(valorAcumulado,actual,action);
					if(cota>mejorValor) {//estamos minimizando
						continue;
					}
					Ejercicio2Vertex vecino=actual.neighbor(action);
					Spm r=pdr_search(vecino, valorAcumulado+(action*DatosEjercicio2.getPrecio(actual.indice())),memory);
					if( r!=null) {
						Spm combinado=Spm.of(action, r.weight()+(action*DatosEjercicio2.getPrecio(actual.indice())));
						soluciones.add(combinado);
					}
				}
				res=soluciones.stream().min(Comparator.naturalOrder()).orElse(null);//estamos minimizando
				if(res!=null) {
					memory.put(actual, res);
				}
			}
		}
		return res;
	}

	private static Double funcionCota(Integer valorAcumulado, Ejercicio2Vertex actual,Integer action) {
		return valorAcumulado+(action*DatosEjercicio2.getPrecio(actual.indice()))+Ejercicio2Heuristic.heuristic(actual.neighbor(action), null, null);
	}
	


}
