package ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.jgrapht.GraphPath;

import datos.DatosEjercicio2;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio2Vertex(Integer indice, Set<Integer> categoriasPorCubir, List<Integer> presupuestoRestante,
		Integer acumValoracion) implements VirtualVertex<Ejercicio2Vertex, Ejercicio2Edges, Integer> {

	public static Integer n = DatosEjercicio2.getNumProductos();

	public static Ejercicio2Vertex of(Integer indice, Set<Integer> categoriasPorCubir,
			List<Integer> presupuestoRestante, Integer acumValoracion) {
		return new Ejercicio2Vertex(indice, categoriasPorCubir, presupuestoRestante, acumValoracion);
	}

	public static Ejercicio2Vertex first() {
		return Ejercicio2Vertex.of(0, new HashSet<Integer>(DatosEjercicio2.getCategorias()),
				new ArrayList<Integer>(DatosEjercicio2.presupuestoPorCategorias()), 0);
	}

	public static Ejercicio2Vertex last() {
		return Ejercicio2Vertex.of(0, new HashSet<Integer>(), new ArrayList<Integer>(), 0);
	}

	public static Predicate<Ejercicio2Vertex> goal() {
		return v -> v.indice() == n;
	}

	public static Predicate<Ejercicio2Vertex> goalHasSolution() {
		return v -> v.categoriasPorCubir().isEmpty() && v.acumValoracion() >= 0
				&& v.presupuestoRestante().stream().allMatch(p -> p >= 0);
	}

	@Override
	public Boolean isValid() {
		return indice >= 0 && indice <= n;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> acciones = new ArrayList<Integer>();
															
		if (indice < n) {
			if (indice == n - 1) {
				acciones.add(0);
				if (acumValoracion + (DatosEjercicio2.getValoracion(indice) - 3) >= 0) {
					if (!categoriasPorCubir.isEmpty()) {
						for (Integer i : categoriasPorCubir) {
							if (DatosEjercicio2.getCategoriaDe(indice) == i && (presupuestoRestante.get(DatosEjercicio2.getCategoriaDe(indice))- DatosEjercicio2.getPrecio(indice)) >= 0) {
								acciones.add(1);
								break;
							} 
						}

					}
				}

			} else {
				acciones = IntStream.rangeClosed(0, 1).boxed().toList();
			}

		}

		return acciones;
	}

	@Override
	public Ejercicio2Vertex neighbor(Integer a) {
		Set<Integer> cat = new HashSet<Integer>(categoriasPorCubir);
		List<Integer> pres = new ArrayList<Integer>(presupuestoRestante);
		if (a == 1) {
			cat.remove(DatosEjercicio2.getCategoriaDe(indice));
			pres.set(DatosEjercicio2.getCategoriaDe(indice),presupuestoRestante.get(DatosEjercicio2.getCategoriaDe(indice)) - DatosEjercicio2.getPrecio(indice));
		}
		return Ejercicio2Vertex.of(indice + 1, cat, pres, acumValoracion + a * (DatosEjercicio2.getValoracion(indice)-3));
	}

	@Override
	public Ejercicio2Edges edge(Integer a) {
		return Ejercicio2Edges.of(this, this.neighbor(a), a);
	}

	public static Ejercicio2Solucion getSolucion(GraphPath<Ejercicio2Vertex, Ejercicio2Edges> path) {
		return Ejercicio2Vertex.getSolucion(path.getEdgeList());
	}

	public static Ejercicio2Solucion getSolucion(List<Ejercicio2Edges> ls) {

		List<Integer> alternativas = List2.empty();

		for (Ejercicio2Edges alternativa : ls) {
			alternativas.add(alternativa.action());
		}

		Ejercicio2Solucion s = Ejercicio2Solucion.create(alternativas);

		return s;
	}
	
}
