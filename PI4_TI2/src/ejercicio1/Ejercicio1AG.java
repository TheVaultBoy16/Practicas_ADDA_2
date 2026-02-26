package ejercicio1;


import java.util.List;

import datos.DatosEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio1AG implements ValuesInRangeData<Integer, Ejercicio1Solucion> {

	
	public Ejercicio1AG(String linea) {
		DatosEjercicio1.iniDatos(linea);
	}


	public Integer size() {
		return DatosEjercicio1.getNumVariedades();
	}


	public ChromosomeType type() {
		return ChromosomeType.Range;
	}
	


	public Integer max(Integer i) {
		return DatosEjercicio1.getNumHuertos()+1;
	}

	public Integer min(Integer i) {
		return 0;
	}
	
	public Double errorMetros(List<Integer> ls) {
		Double error=0.;
		Double mRequeridos=0.;
		for(int j = 0; j < DatosEjercicio1.getNumHuertos(); j++) {
			Double metrosDisponibles=Double.valueOf(DatosEjercicio1.getMetrosDisponibles(j));
			for(int i = 0; i < size(); i++) {
				if(ls.get(i)<DatosEjercicio1.getNumHuertos()) {
					if(ls.get(i)==j) {
						mRequeridos+=Double.valueOf(DatosEjercicio1.getMetrosRequeridos(i));
						}
					}
				}
			if(metrosDisponibles<mRequeridos) {
				error+=mRequeridos-metrosDisponibles;
			}
			mRequeridos=0.;
		}
		return error;
	}
	
	public Double errorIncompatibilidad(List<Integer> ls) {
		Double error=0.;
		for(int i = 0; i < size(); i++) {
			if(ls.get(i)<DatosEjercicio1.getNumHuertos()) {
				for(int k = i+1; k < size(); k++) {
					if(ls.get(k)<DatosEjercicio1.getNumHuertos()) {
						if(ls.get(i)==ls.get(k)){
							if(DatosEjercicio1.getIncompatibilidad(i, k)==1) {
								error+=1.0;
							}
						}
					}
				}
			}
		}
		return error;
	}


	public Double fitnessFunction(List<Integer> ls) {
		Double goal=0.;
		Double error=errorMetros(ls);
		Double error2=errorIncompatibilidad(ls);
		for(int i = 0; i < size(); i++) {
			if(ls.get(i)<DatosEjercicio1.getNumHuertos()) {
				goal++;
			}
		}
		Double fitness=goal-1000.0*(error+error2);
		return fitness;

		
	}


	public Ejercicio1Solucion solucion(List<Integer> ls) {
		Ejercicio1Solucion s = Ejercicio1Solucion.create(ls);
		return s;
	}


}
