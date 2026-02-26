package ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio3;

public class Ejercicio3Solucion {
	
	public static Ejercicio3Solucion create(List<Integer> ls) {
		return new Ejercicio3Solucion(ls);
	}
	
	private Integer costeTotal;
	private Map<Integer,List<Integer>> cantidadesDeProductos;
	
	private Ejercicio3Solucion(List<Integer> ls) {
		costeTotal=0;
		cantidadesDeProductos= new HashMap<Integer, List<Integer>>();
			for(int i=0;i<ls.size();i++) {
				Integer p=i%DatosEjercicio3.getNumProductos();
				Integer j=i/DatosEjercicio3.getNumProductos();
				if(cantidadesDeProductos.containsKey(p)) {
					cantidadesDeProductos.get(p).add(ls.get(i));
				}
				else {
					List<Integer> valor= new ArrayList<Integer>();
					valor.add(ls.get(i));
					cantidadesDeProductos.put(p, valor);
					}
				costeTotal+=ls.get(i)* DatosEjercicio3.getCosteAlmacenamiento(p, j);
				}
		}
	
	public String toString() {
		String s=cantidadesDeProductos.toString();
		String res="Reparto obtenido (cantidad de producto repartido a cada destino):"+s+"\n"+"Coste total de almacenamiento:"+costeTotal;
		return res;
	}

}
