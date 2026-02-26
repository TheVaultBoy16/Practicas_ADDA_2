package ejemplo3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnosPDR {
	public static record Spm(Integer a,Integer weight) implements Comparable<Spm> {//Solucion parcial, peso puede sre double
		
		public static Spm of(Integer a,Integer weight) {
			return new Spm(a,weight);
		}

		@Override
		public int compareTo(Spm o) {
			return this.weight.compareTo(o.weight);
		}
		
	}
	
	
	
	public static Map<AlumnosVertex,Spm> memory;
	public static Integer mejorValor=Integer.MIN_VALUE;// pa comproba que las ramaz que voy obteniendo puedo o no podarlas, estamos maximizando no poner 0 poner MinValue
	
	public static SolucionAlumnos search() {
		memory= new HashMap<>();
		mejorValor=Integer.MIN_VALUE;// lo vuelvo a asignar,estamos maximizando
		AlumnosVertex inicial=AlumnosVertex.initial();
		Integer valorAcumulado=0;
		pdr_search(inicial,valorAcumulado,memory);// tambien se puede poner el new HashMap del tiri
		
		return getSolucion();
	}

	private static SolucionAlumnos getSolucion() {
		List<Integer> acciones= new ArrayList<Integer>();//lista con las mejores acciones
		AlumnosVertex actual=AlumnosVertex.initial();
		Spm spm=memory.get(actual);
		while(spm!=null&& spm.a()!=null) {
			AlumnosVertex old=actual;//es el inicial al principio,luego no
			acciones.add(spm.a());
			actual=old.neighbor(spm.a());
			spm=memory.get(actual);
		}
		return SolucionAlumnos.of(acciones);
	}

	private static Spm pdr_search(AlumnosVertex actual, Integer valorAcumulado, Map<AlumnosVertex, Spm> memory) {
		Spm res=null;
		if(memory.containsKey(actual)) {// memoria contiene vertice actual
			res=memory.get(actual);
		}else {
			if(AlumnosVertex.goal().test(actual)&& AlumnosVertex.goalHasSolution().test(actual)) {//.test(actual)
				res=Spm.of(null, 0);// porque esto se supone que ya estamos en el estado final,i=n
				memory.put(actual, res);
				if(valorAcumulado > mejorValor) {//estamos maximizando
					
				}
			}else {
				List<Spm> soluciones=new ArrayList<>();
				for(Integer action:actual.actions()) {// todos los grupos posibles para el alumno de ese momento
					//Aqui se llama a la funcion de cota si queremos hacerlo por heuristica si no se queda asi
					Double cota=funcionCota(valorAcumulado,actual,action);
					if(cota<mejorValor) {//estamos maximizando
						continue;// salta a la siguiente accion, no me hagas la llamada recursiva
					}
					AlumnosVertex vecino=actual.neighbor(action);
					Spm r=pdr_search(vecino, valorAcumulado+DatosAlumnos.getAfinidad(actual.index(), action), memory);
					if( r!=null) {
						Spm combinado=Spm.of(action, r.weight()+DatosAlumnos.getAfinidad(actual.index(), action));// como el weight de las aristas
						soluciones.add(combinado);
					}
				}
				res=soluciones.stream().max(Comparator.naturalOrder()).orElse(null);//estamos maximizando
				if(res!=null) {
					memory.put(actual, res);
				}
			}
		}
		return res;
	}

	private static Double funcionCota(Integer valorAcumulado, AlumnosVertex actual,Integer action) {
		return valorAcumulado+DatosAlumnos.getAfinidad(actual.index(), action)+AlumnosHeuristic.heuristic(actual.neighbor(action), null, null);
	}
	
	
	
	
	
	
}
