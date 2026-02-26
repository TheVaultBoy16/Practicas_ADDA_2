package ejercicio4Manual;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicio4;
import ejercicio4.Ejercicio4Heuristic;
import ejercicio4.Ejercicio4Solucion;
import ejercicio4.Ejercicio4Vertex;
import us.lsi.common.List2;





public class Ejercicio4State {
	
	Ejercicio4Vertex actual;
	Double acumulado;
	List<Integer> acciones;
	List<Ejercicio4Vertex> anteriores;
	
	public static Ejercicio4State of(Ejercicio4Vertex prob, Double acum, List<Integer> lsa,List<Ejercicio4Vertex> lsp) {
		return new Ejercicio4State(prob, acum, lsa, lsp);
	}
	
	private Ejercicio4State(Ejercicio4Vertex p, Double a, List<Integer> ls1, List<Ejercicio4Vertex> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}
	
	
	
	public static Ejercicio4State initial() {
		Ejercicio4Vertex pi = Ejercicio4Vertex.first();
		List<Ejercicio4Vertex> lv= new ArrayList<Ejercicio4Vertex>();
		return of(pi, 0., List2.empty(), lv);
	}
	
	public void forward(Integer a) {
		if(actual.ultima()!=DatosEjercicio4.getNumPersonas()) {
			acumulado +=DatosEjercicio4.getAfinidadCon(actual.ultima(), a);
			acciones.add(a);
			anteriores.add(actual);
			actual = actual.neighbor(a);
		}

	}

	public void back() {
		int last = acciones.size() - 1;
		var prob_ant = anteriores.get(last);
		if(prob_ant.ultima()!=DatosEjercicio4.getNumPersonas()) {
			acumulado -= DatosEjercicio4.getAfinidadCon(prob_ant.ultima(), acciones.get(last));
			acciones.remove(last);
			anteriores.remove(last);
			actual = prob_ant;
		}

	}

	public List<Integer> alternativas() {
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight=0.0;
		Double res=0.0;
		if(actual.ultima()!=DatosEjercicio4.getNumPersonas()) {
			weight=DatosEjercicio4.getAfinidadCon(actual.ultima(), a).doubleValue();
			res=acumulado+weight+Ejercicio4Heuristic.heuristic(actual.neighbor(a), null, null);
		}
		return res;
	}

	public Boolean esSolucion() {
		return actual.restante().isEmpty();
	}

	public boolean esTerminal() {
		return actual.indice()==DatosEjercicio4.getNumPersonas();
	}
	

	public Ejercicio4Solucion getSolucion() {
		return Ejercicio4Solucion.create(acciones);
	}

}
