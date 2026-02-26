package ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import datos.DatosEjercicio3;
import datos.DatosEjercicio3.Destino;
import datos.DatosEjercicio3.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {

	public static void main(String[] args) throws IOException {
		//Descomentar y comentar para probar con los distintos ficheros
		ejercicio3_1_model();
//		ejercicio3_2_model();
//		ejercicio3_3_model();
	}
	
	
	public static List<Destino> destinos;
	public static List<Producto> productos;
	
	public static List<Producto> getProductos(){
		return productos;
	}
	
	public static List<Destino> getDestinos(){
		return destinos;
	}
	
	public static Integer getNumProductos() {
		return productos.size();
	}
	public static Integer getNumDestinos() {
		return destinos.size();
	}
	
	public static Integer getCantidadDisponible(Integer i) {
		return productos.get(i).cantidadDisponible();
	}
	
	public static Integer getDemandaMinima(Integer j) {
		return destinos.get(j).demandaMinima();
	}
	
	public static Integer getCosteAlmacenamiento(Integer i,Integer j) {
		Integer res=0;
		Map<Integer,Integer> costeAlmacenamiento=productos.get(i).costeAlmacenamiento();
		if(costeAlmacenamiento.containsKey(j)) {
			res=costeAlmacenamiento.get(j);
		}
		return res;
	}
	
	public static void ejercicio3_1_model() throws IOException{
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
		
		destinos=DatosEjercicio3.getDestinos();
		productos=DatosEjercicio3.getProductos();
		
		AuxGrammar.generate(DatosEjercicio3.class, "models/ejercicio3.lsi", "gurobi_models/ejercicio3-1.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio3-1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio3_2_model() throws IOException{
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
		
		destinos=DatosEjercicio3.getDestinos();
		productos=DatosEjercicio3.getProductos();
		
		AuxGrammar.generate(DatosEjercicio3.class, "models/ejercicio3.lsi", "gurobi_models/ejercicio3-2.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio3-2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio3_3_model() throws IOException{
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
		
		destinos=DatosEjercicio3.getDestinos();
		productos=DatosEjercicio3.getProductos();
		
		AuxGrammar.generate(DatosEjercicio3.class, "models/ejercicio3.lsi", "gurobi_models/ejercicio3-3.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio3-3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}

}
