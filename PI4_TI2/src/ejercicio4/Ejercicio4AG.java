package ejercicio4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import datos.DatosEjercicio4;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio4AG implements SeqNormalData<Ejercicio4Solucion> {

	
	public Ejercicio4AG(String linea) {
		DatosEjercicio4.iniDatos(linea);
	}

	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}
	
	 public Double errorIdioma(List<Integer> ls) {
			Double errorIdioma=0.;
			List<Integer> copia= new ArrayList<Integer>(ls);
			if(copia.size()%2==1) {
				copia.remove(copia.size()-1);
			}
			for(int i = 0; i < size()-1; i+=2) {
				Set<String> idiomas1= DatosEjercicio4.getIdiomas(copia.get(i));
				Set<String> idiomas2= DatosEjercicio4.getIdiomas(copia.get(i+1));
				Boolean idiomaComun=false;
				for(String s:idiomas1) {
					for(String s2:idiomas2) {
						if(s.equals(s2)) {
							idiomaComun=true;
							break;
						}
					}
					if(idiomaComun) {
						break;
					}
				}
				
				if(!idiomaComun) {
					errorIdioma++;
				}
			}
			return errorIdioma;
			
	 }
	 
	 public Double errorEdad(List<Integer> ls) {
			Double errorEdad=0.;
			List<Integer> copia= new ArrayList<Integer>(ls);
			if(copia.size()%2==1) {
				copia.remove(copia.size()-1);
			}
			for(int i = 0; i < size()-1; i+=2) {
				if(Math.abs(DatosEjercicio4.getEdad(copia.get(i))-DatosEjercicio4.getEdad(copia.get(i+1)))>5){
					errorEdad++;
				}
			}
			return errorEdad;
	 }
	 
	 public Double errorNacionalidad(List<Integer> ls) {
		 	Double errorNacionalidad=0.;
			List<Integer> copia= new ArrayList<Integer>(ls);
			if(copia.size()%2==1) {
				copia.remove(copia.size()-1);
			}
			for(int i = 0; i < size()-1; i+=2) {
				if(DatosEjercicio4.getNacionalidad(copia.get(i)).equals(DatosEjercicio4.getNacionalidad(copia.get(i+1)))) {
					errorNacionalidad++;
				}
			}
			return errorNacionalidad;
	 }
	 
	 


	public Double fitnessFunction(List<Integer> ls) {
		Double goal=0.;
		Double errorIdioma=errorIdioma(ls);
		Double errorEdad=errorEdad(ls);
		Double errorNacionalidad=errorNacionalidad(ls);
		
		List<Integer> copia= new ArrayList<Integer>(ls);
		if(copia.size()%2==1) {
			copia.remove(copia.size()-1);
		}
		
		for(int i = 0; i < size()-1; i+=2) {
			goal+=DatosEjercicio4.getAfinidadCon(copia.get(i), copia.get(i+1));
						
		}
		Double fitness=goal-1000.0*(errorIdioma+errorEdad+errorNacionalidad);
		
		return fitness ;
	}


	public Ejercicio4Solucion solucion(List<Integer> ls) {
		return Ejercicio4Solucion.create(ls);
	}


	public Integer itemsNumber() {
		return DatosEjercicio4.getNumPersonas();
	}

}
