package ag;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;

public class Ejemplo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Integer size() {
		return Datos.getN();// tamaño del cromosoma, maximo n genes (desde o hasta n-1)
	}
	
	
	public Integer min(Integer i) {
		return i; // i porque eso de cada tipo minimo tienes que pillar i, cada variable tiene que ser minimo i 
	}
	
	public Integer max(Integer i) {
		return (i*i)+3; // no se le suma 1 porque es ya menor estricto si fuera <= si 
	}
	
	
	public Double fitnessFunction(List<Integer> dc) { // aqui calculo el fitness de uno, es decir recorro el cromosoma y hago las operaciones de la funcion objetivo, si se puede hacer todo en el mismo metodo mejor, dc es decode es el cromosoma (lista)
		Integer costeTotal=0;
		Integer suma=0;
		for(Integer i=0;i<dc.size();i++) {
			costeTotal+=dc.get(i)*Datos.getCoste(i);// el de la func obejtivo
			suma+=dc.get(i);// contar objetos
		}
		Integer p=0;
		if(suma<2*Datos.getN()) {// si no se cumple restriccion, penalizacion
			p=Math.pow(2*Datos.getN()-suma, 2); // la wea de la penalizacion si no se cumple restriccion, aqui se hace el distancetoGeZero porque penaliza si es > que 0
		}
		Double fitnes=-costeTotal-1000.0*p; // la funcion objetivo con la penalizacion que es random suele ser 1000, funcion fitness cuando se quiere minimizar
		return fitnes;
	}
	

}
