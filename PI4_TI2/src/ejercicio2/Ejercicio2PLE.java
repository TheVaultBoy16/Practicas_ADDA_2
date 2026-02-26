package ejercicio2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import datos.DatosEjercicio2;
import datos.DatosEjercicio2.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {

	public static void main(String[] args) throws IOException {
		//Descomentar y comentar para probar con los distintos ficheros
		ejercicio2_1_model();
//		ejercicio2_2_model();
//		ejercicio2_3_model();
	}
	
	
	public static List<Producto> productos;
	public static List<Integer> categorias;
	public static Integer presupuesto;
	
	
	public static Integer getPresupuesto(){
		return presupuesto;
	}
	
	public static Integer getNumProductos() {
		return productos.size();
	}
	public static Integer getNumCategorias() {
		return categorias.size();
	}
	
	
	public static Integer getCategoria(Integer i,Integer j) {
		Integer res=0;
		if(productos.get(i).categoria()==categorias.get(j)) {
			res=1;
		}
		return res;
	}
	
	public static Integer getPrecio(Integer i) {
		return productos.get(i).precio();
	}
	
	public static Integer getValoracion(Integer i) {
		return productos.get(i).valoracion();
	}
	
	
	public static void ejercicio2_1_model() throws IOException{
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
		
		productos=DatosEjercicio2.getProductos();
		categorias=DatosEjercicio2.getCategorias();
		presupuesto=DatosEjercicio2.getPresupuesto();
		
		AuxGrammar.generate(DatosEjercicio2.class, "models/ejercicio2.lsi", "gurobi_models/ejercicio2-1.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio2-1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio2_2_model() throws IOException{
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada2.txt");
		
		productos=DatosEjercicio2.getProductos();
		categorias=DatosEjercicio2.getCategorias();
		presupuesto=DatosEjercicio2.getPresupuesto();
		
		AuxGrammar.generate(DatosEjercicio2.class, "models/ejercicio2.lsi", "gurobi_models/ejercicio2-2.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio2-2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio2_3_model() throws IOException{
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
		
		productos=DatosEjercicio2.getProductos();
		categorias=DatosEjercicio2.getCategorias();
		presupuesto=DatosEjercicio2.getPresupuesto();
		
		AuxGrammar.generate(DatosEjercicio2.class, "models/ejercicio2.lsi", "gurobi_models/ejercicio2-3.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio2-3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	

}
