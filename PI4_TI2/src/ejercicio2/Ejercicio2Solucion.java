package ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datos.DatosEjercicio2;
import datos.DatosEjercicio2.Producto;



public class Ejercicio2Solucion {
	
	public static Ejercicio2Solucion create(List<Integer> ls) {
		return new Ejercicio2Solucion(ls);
	}
	
	private Integer precioTotal;
	private List<Producto> productosSeleccionados;
	
	
	private Ejercicio2Solucion(List<Integer> ls) {
		precioTotal=0;
		productosSeleccionados = new ArrayList<Producto>();
		for(int i=0;i<ls.size();i++) {
			if(ls.get(i)>0) {
				precioTotal+=DatosEjercicio2.getPrecio(i);
				productosSeleccionados.add(DatosEjercicio2.getProducto(i));
			}
		}
	}
	
	public String toString() {
		String s=productosSeleccionados.stream().map(v->"Producto "+v.id_prod()).collect(Collectors.joining(",","Productos elegidos:{","}"));
		String res="Precio total de la cesta:"+precioTotal+"\n"+s;
		return res;
	}


}
