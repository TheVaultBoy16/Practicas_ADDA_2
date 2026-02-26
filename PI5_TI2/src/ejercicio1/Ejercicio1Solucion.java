package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio1;

public class Ejercicio1Solucion {

	
	public static Ejercicio1Solucion create(List<Integer> ls) {
		return new Ejercicio1Solucion(ls);
	}
	
	
	private Integer totalCultivadas;
	private Map<String,String> variedadYhuerto;
	
	private Ejercicio1Solucion(List<Integer> ls) {
		totalCultivadas=0;
		variedadYhuerto= new HashMap<String,String>();
		for(int i=0;i<ls.size();i++) {
			if(ls.get(i)<DatosEjercicio1.getNumHuertos()) {
				if(!variedadYhuerto.containsKey(i)) {
					variedadYhuerto.put("V"+i," Huerto "+ ls.get(i));
				}
				totalCultivadas++;
			}
		}
		 
		
	}
	
	public String toString() {
		String s=variedadYhuerto.toString();
		String res="Reparto de verduras y huerto en el que es plantada cada una de ellas (si procede):"
						+s+"\n"+"Numero de variedades cultivadas: "+totalCultivadas;
		return res;
	}

}
