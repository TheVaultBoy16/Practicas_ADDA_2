package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import datos.DatosEjercicio1;
import datos.DatosEjercicio1.Huerto;
import datos.DatosEjercicio1.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	
	public static void main(String[] args) throws IOException {
		//Descomentar y comentar para probar con los distintos ficheros
		ejercicio1_1_model();
//		ejercicio1_2_model();
//		ejercicio1_3_model();

	}
	
	public static List<Huerto> huertos;
	public static List<Variedad> variedades;
	
	public static Integer getNumHuertos() {//m
		return huertos.size();
	}
	public static Integer getNumVariedades() {//n
		return variedades.size();
	}
	
	public static Integer getMetrosDisponibles(Integer j) {//Hj
		return huertos.get(j).metrosDisponibles();
	}
	
	public static Integer getMetrosRequeridos(Integer i) {//Si
		return variedades.get(i).metrosRequeridos();
	}
	
	public static Integer getIncompatibilidad(Integer i,Integer k) {//Incompik
		Integer res=0;
		List<String> variedadesDeI=variedades.get(i).listaIncompatibilidades();
		for(String s:variedadesDeI) {
			Character ch=s.charAt(1);
			String a=ch.toString();
			if(a.equals(k.toString())) {
				res=1;
			}
		}
		return res;
	}
	
	public static void ejercicio1_1_model() throws IOException{
		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
		
		huertos=DatosEjercicio1.getHuertos();
		variedades=DatosEjercicio1.getVariedades();
		
		AuxGrammar.generate(DatosEjercicio1.class, "models/ejercicio1.lsi", "gurobi_models/ejercicio1-1.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio1-1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion);
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio1_2_model() throws IOException{
		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada2.txt");
		
		huertos=DatosEjercicio1.getHuertos();
		variedades=DatosEjercicio1.getVariedades();
		
		AuxGrammar.generate(DatosEjercicio1.class, "models/ejercicio1.lsi", "gurobi_models/ejercicio1-2.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio1-2.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	
	public static void ejercicio1_3_model() throws IOException{
		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
		
		huertos=DatosEjercicio1.getHuertos();
		variedades=DatosEjercicio1.getVariedades();
		
		AuxGrammar.generate(DatosEjercicio1.class, "models/ejercicio1.lsi", "gurobi_models/ejercicio1-3.lp");
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejercicio1-3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solucion.toString((s,d)->d>0.));
		
	}
	


}
