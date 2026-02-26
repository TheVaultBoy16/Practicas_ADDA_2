package ejercicio2;

import java.util.Comparator;
import java.util.function.Predicate;

import datos.DatosEjercicio2;

public class Ejercicio2Heuristic {
	
	public static Double heuristic(Ejercicio2Vertex v1, Predicate<Ejercicio2Vertex> goal,Ejercicio2Vertex v2) {
		Double h=0.;
		if(v1.indice()<DatosEjercicio2.getNumProductos()) {
			Integer prodMasBarato=DatosEjercicio2.getProductos().subList(v1.indice(), DatosEjercicio2.getNumProductos()).stream().min(Comparator.comparing(x->x.precio())).get().id_prod();
			
				if(!v1.categoriasPorCubir().isEmpty()) {
					for(Integer i:v1.categoriasPorCubir()) {
						if(DatosEjercicio2.getCategoriaDe(prodMasBarato)==i && ((v1.presupuestoRestante().get(DatosEjercicio2.getCategoriaDe(prodMasBarato)))-(DatosEjercicio2.getPrecio(prodMasBarato)))>0) {
							h=prodMasBarato*1.0;
						}
					}
				}
		}
		
		return h;
	}
		



}
