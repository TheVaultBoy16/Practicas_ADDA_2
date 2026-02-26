package ejercicio4;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicio4;

public class Ejercicio4Solucion {

	
	public record Relacion(Integer p1,Integer p2) {
		public static Relacion of(Integer p1,Integer p2) {
			return new Relacion(p1,p2);
		}
	}
	
	public static Ejercicio4Solucion create(List<Integer> ls) {
		return new Ejercicio4Solucion(ls);
	}
	
	private List<Relacion> relaciones;
	private Integer afinidadTotal;
	
	private Ejercicio4Solucion(List<Integer> ls) {
		afinidadTotal=0;
		relaciones= new ArrayList<Relacion>();
		List<Integer> copia= new ArrayList<Integer>(ls);
		if(copia.size()%2==1) {
			copia.remove(copia.size()-1);
		}
		for(int i = 0; i < ls.size()-1; i+=2) {
			afinidadTotal+=DatosEjercicio4.getAfinidadCon(copia.get(i), copia.get(i+1));
			Relacion r= Relacion.of(ls.get(i), ls.get(i+1));
			relaciones.add(r);
		}
		
	}
	
	
	public String toString() {
		String s="Relacion de parejas: "+relaciones+
				"\nSuma de afinidades: "+afinidadTotal;
		return s;
	}

}
