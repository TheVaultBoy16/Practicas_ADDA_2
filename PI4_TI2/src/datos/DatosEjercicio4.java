package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.Set2;


public class DatosEjercicio4 {

	public static void main(String[] args) {
		//Descomentar y comentar para probar con los distintos ficheros
		iniDatos("ficheros/Ejercicio4DatosEntrada1.txt");
//		iniDatos("ficheros/Ejercicio4DatosEntrada2.txt");
//		iniDatos("ficheros/Ejercicio4DatosEntrada3.txt");
		List<Persona> lPersonas= new ArrayList<Persona>(personas);
		for(Persona p:lPersonas) {
			System.out.println(p);
		}
		System.out.println(tienenIdiomaComun(0, 5));

	}
	
	public static record Persona(Integer id_persona,String nombre,Integer edad,Set<String>  idiomas,String nacionalidad,Map<Integer,Integer> afinidades) {
		
		private static int id;
		
		public static Persona create(String s) {
			String[] v=s.split("->");
			String nombre=v[0].trim();
			String [] v2=v[1].split(";");
			Integer edad=Integer.parseInt(v2[0].replace("edad=", "").trim());
			Set<String> idiomas=Set2.parse(v2[1].replace("idiomas=", "").trim().split(","), String::trim);
			String nacionalidad=v2[2].replace("nacionalidad=", "").trim();
			Map<Integer,Integer> afinidades= new HashMap<Integer, Integer>();
			String[] v3=v2[3].replace("afinidades=", "").trim().split(",");
			for(String st:v3) {
				String[] v4=st.replace("(", "").replace(")", "").trim().split(":");
				afinidades.put(Integer.parseInt(v4[0].trim()), Integer.parseInt(v4[1].trim()));
			}
			return new Persona(id++, nombre, edad, idiomas, nacionalidad, afinidades);
		}
		
	}
	
	public static List<Persona> personas;
	
	
	public static void iniDatos(String fichero) {
		List<String> lineas=Files2.linesFromFile(fichero);
		personas= new ArrayList<>();
		for(String linea:lineas) {
			if(!linea.contains("//")) {
				personas.add(Persona.create(linea));
			}
		}
		
	}
	
	public static List<Persona> getPersonas(){
		return personas;
	}
	
	public static Integer getNumPersonas() {//n
		return personas.size();
	}
	
	public static Set<String> getIdiomas(Integer i){//idi
		return personas.get(i).idiomas();
	}
	
	public static Integer getEdad(Integer i){//edadi
		return personas.get(i).edad();
	}
	
	public static String getNacionalidad(Integer i){//naci
		return personas.get(i).nacionalidad();
	}
	
	public static Map<Integer,Integer> getAfinidades(Integer i){// obtiene lista afinidades de una persona i
		return personas.get(i).afinidades();
	}
	
	public static Integer getAfinidadCon(Integer i,Integer j){//afij
		Integer res=0;
		Map<Integer,Integer> afinidades=personas.get(i).afinidades();
		if(afinidades.containsKey(j)) {
			res=afinidades.get(j);
		}
		return res;
	}
	
	public static Boolean tienenIdiomaComun(Integer i,Integer j) {
		Set<String> idiomas1= getIdiomas(i);
		Set<String> idiomas2= getIdiomas(j);
		Boolean idiomaComun=false;
		for(String s:idiomas1) {
			for(String s2:idiomas2) {
				if(s.equals(s2)) {
					idiomaComun=true;
					break;
				}
			}
		}
		return idiomaComun;
	}
	



}
