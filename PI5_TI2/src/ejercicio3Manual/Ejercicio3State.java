package ejercicio3Manual;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicio3;
import ejercicio3.Ejercicio3Heuristic;
import ejercicio3.Ejercicio3Solucion;
import ejercicio3.Ejercicio3Vertex;
import us.lsi.common.List2;

public class Ejercicio3State {
	
	Ejercicio3Vertex actual;
	Double acumulado;
	List<Integer> acciones;
	List<Ejercicio3Vertex> anteriores;
	
	public static Ejercicio3State of(Ejercicio3Vertex prob, Double acum, List<Integer> lsa,List<Ejercicio3Vertex> lsp) {
		return new Ejercicio3State(prob, acum, lsa, lsp);
	}
	
	private Ejercicio3State(Ejercicio3Vertex p, Double a, List<Integer> ls1, List<Ejercicio3Vertex> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}
	
	
	
	public static Ejercicio3State initial() {
		Ejercicio3Vertex pi = Ejercicio3Vertex.first();
		List<Ejercicio3Vertex> lv= new ArrayList<Ejercicio3Vertex>();
		return of(pi, 0., List2.empty(), lv);
	}
	
	public void forward(Integer a) {
		acumulado+=a*DatosEjercicio3.getCosteAlmacenamiento(actual.z()/DatosEjercicio3.getNumDestinos(),actual.z()%DatosEjercicio3.getNumDestinos());
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}

	public void back() {
		int last = acciones.size() - 1;
		var prob_ant = anteriores.get(last);
		acumulado-=acciones.get(last)*DatosEjercicio3.getCosteAlmacenamiento(actual.z()/DatosEjercicio3.getNumDestinos(),actual.z()%DatosEjercicio3.getNumDestinos());
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;

	}

	public List<Integer> alternativas() {
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight=a*DatosEjercicio3.getCosteAlmacenamiento(actual.z()/DatosEjercicio3.getNumDestinos(),actual.z()%DatosEjercicio3.getNumDestinos())*1.0;
		return acumulado+weight+Ejercicio3Heuristic.heuristic(actual.neighbor(a), null, null);
	}

	public Boolean esSolucion() {
		return actual.demandasRestantes().stream().allMatch(x->x<=0);
	}

	public boolean esTerminal() {
		return actual.z()==DatosEjercicio3.getNumDestinos()*DatosEjercicio3.getNumProductos();
	}
	

	public Ejercicio3Solucion getSolucion() {
		return Ejercicio3Solucion.create(acciones);
	}

}
