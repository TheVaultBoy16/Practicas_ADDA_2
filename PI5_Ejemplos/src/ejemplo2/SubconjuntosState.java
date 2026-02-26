package ejemplo2;

import java.util.ArrayList;
import java.util.List;

import datos.DatosSubconjuntos;
import soluciones.SolucionSubconjuntos;
import us.lsi.common.List2;

public class SubconjuntosState {

	SubconjuntosVertex actual;
	Double acumulado;
	List<Integer> acciones;
	List<SubconjuntosVertex> anteriores;
	
	private SubconjuntosState(SubconjuntosVertex p, Double a, 
		List<Integer> ls1, List<SubconjuntosVertex> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}

	public static SubconjuntosState initial() {
		SubconjuntosVertex pi = SubconjuntosVertex.initial();
		List<SubconjuntosVertex> lv= new ArrayList<SubconjuntosVertex>();// vale List2.empty()
		return of(pi, 0., List2.empty(), lv);
	}

	public static SubconjuntosState of(SubconjuntosVertex prob, Double acum, List<Integer> lsa,
			List<SubconjuntosVertex> lsp) {
		return new SubconjuntosState(prob, acum, lsa, lsp);
	}

	public void forward(Integer a) {// permite avanzar al vecino		
		acumulado += a * DatosSubconjuntos.peso(actual.index());
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}

	public void back() {// retrocede a alternativa para avanzar a otro estado
		int last = acciones.size() - 1;
		var prob_ant = anteriores.get(last);// cojo ultima accion
		
		acumulado -= acciones.get(last) * DatosSubconjuntos.peso(prob_ant.index());
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}

	public List<Integer> alternativas() {
		return actual.actions();
	}

	public Double cota(Integer a) {
		Double weight = a > 0 ? DatosSubconjuntos.peso(actual.index()) : 0.;
		return acumulado + weight +SubconjuntosHeuristic.heuristic(actual.neighbor(a), null, null);
	}

	public Boolean esSolucion() {
		return actual.remaining().isEmpty();
	}

	public boolean esTerminal() {
		return actual.index() == DatosSubconjuntos.NUM_SC;
	}
	

	public SolucionSubconjuntos getSolucion() {
		return SolucionSubconjuntos.of(acciones);
	}

}
