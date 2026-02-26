package datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio1 {

	public static void main(String[] args) {
		//Descomentar y comentar para probar con los distintos ficheros
		iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
//		iniDatos("ficheros/Ejercicio1DatosEntrada2.txt");
//		iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");
		List<Huerto> lHuertos= new ArrayList<Huerto>(huertos);
		List<Variedad> lVariedades= new ArrayList<Variedad>(variedades);
		for(Huerto h:lHuertos) {
			System.out.println(h);
		}
		for(Variedad v:lVariedades) {
			System.out.println(v);
		}
	}
	
	public static record Huerto(Integer id_huerto,String nombre,Integer metrosDisponibles) {
		
		private static int id;
		
		public static Huerto create(String s) {
			String [] v=s.split(":");
			String nombre=v[0].trim();
			String [] v2=v[1].split(";");
			Integer metrosDisponibles=Integer.parseInt(v2[0].replace("metrosdisponibles=", "").trim());
			return new Huerto(id++,nombre,metrosDisponibles);
		}
	}
	
	public static record Variedad(Integer id_variedad,String nombre,Integer metrosRequeridos,List<String> listaIncompatibilidades) {
		private static int id;
		public static Variedad create(String s) {
			String [] v=s.split("->");
			String nombre=v[0].trim();
			String [] v2=v[1].split(";");
			Integer metrosRequeridos=Integer.parseInt(v2[0].replace("metrosrequeridos=", "").trim());
			List<String> listaIncompatibilidades=List2.parse(v2[1].replace("incomp=", "").trim().split(","), String::trim);
			return new Variedad(id++,nombre,metrosRequeridos,listaIncompatibilidades);
		}
	}
	
	public static List<Huerto> huertos;
	public static List<Variedad> variedades;

	
	public static void iniDatos(String fichero) {
		List<String> lineas=Files2.linesFromFile(fichero);
		huertos=new ArrayList<>();
		variedades=new ArrayList<>();
		for(String linea:lineas) {
			if(!linea.contains("//")) {
				if(linea.contains("H") && !linea.contains("->")) {
					huertos.add(Huerto.create(linea));
				}
				else if(linea.contains("V") && !linea.contains(":")) {
					variedades.add(Variedad.create(linea));
				}
			}
		}
	}
	
	public static List<Huerto> getHuertos() {
		return huertos;
	}
	
	public static List<Variedad> getVariedades(){
		return variedades;
	}
	
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
	
	public static List<Integer> getListaDeMetrosDisponibles(){
		return huertos.stream().map(x->x.metrosDisponibles()).toList();
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
	
	public static List<Set<Integer>> sets(){
		List<Set<Integer>> res= new ArrayList<Set<Integer>>();
		for(int i=0;i<getNumHuertos();i++) {
			res.add(new HashSet<Integer>());
		}
		return res;
	}
	
	
	
	
	

}
