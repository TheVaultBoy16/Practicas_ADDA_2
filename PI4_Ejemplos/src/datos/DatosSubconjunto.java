package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.lsi.common.Files2;
import us.lsi.common.Set2;


public class DatosSubconjunto {

	public static void main(String[] args) {
		iniDatos("ficheros/Ejemplo2DatosEntrada1.txt");
		System.out.println(universo);
		System.out.println(subconjuntos);
	}
	
	public static record Subconjunto(Integer id,Set<Integer> elementos,Double peso) {
		public static int cont;
		public static Subconjunto create(String s) {
			String [] v=s.split(":");
			

			
			Set<Integer> conjunto=Set2.parse(v[0].trim(), "{,}",Integer::parseInt); // con eso me quedo con lo de dentro de las llaves
			Double peso=Double.parseDouble(v[1].trim());
			Subconjunto sc= new Subconjunto(cont++,conjunto ,peso );
			return sc;
		}
	}
	
	private static List<Integer> universo;
	private static List<Subconjunto> subconjuntos;
	
	public static void iniDatos(String fichero) {
		subconjuntos= new ArrayList<Subconjunto>();
		Set<Integer> universoAux= new TreeSet<Integer>();
		List<String> lineas=Files2.linesFromFile(fichero);
		for(String linea:lineas) {
			Subconjunto sc=Subconjunto.create(linea);
			subconjuntos.add(sc);
			universoAux.addAll(sc.elementos());// union guapa
		}
		universo= new ArrayList<Integer>(universoAux);
	}
	
	public static Integer getNumSubconjuntos() {//m
		return subconjuntos.size();
	}
	
	public static Integer getNumElementos() {//n
		return universo.size();
	}
	
	public static Subconjunto getSubconjunto(Integer i) {
		return subconjuntos.get(i);
	}
	
	public static Double getPeso(Integer j) {//Wi
		return getSubconjunto(j).peso();
	}
	
	public static Integer getElemento(Integer i) {//ei
		return universo.get(i);
	}
	
	public static Integer contieneElemento(Integer i,Integer j){//binaria, paso elemento i y subconjunto j
		Integer n=0;
		if(getSubconjunto(j).elementos().contains(i)) {//?1:0 mejor
			n=1;
		}
		return n;
	}
	
	public static List<Subconjunto> getSubconjuntos(){
		return subconjuntos;
	}
	

}
