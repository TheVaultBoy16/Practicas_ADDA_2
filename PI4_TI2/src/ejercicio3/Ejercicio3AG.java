package ejercicio3;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio3AG implements ValuesInRangeData<Integer, Ejercicio3Solucion> {
	
	public Ejercicio3AG(String linea) {
		DatosEjercicio3.iniDatos(linea);
	}

	public ChromosomeType type() {
		return ChromosomeType.Range;
	}


	public Integer max(Integer i) {
		Integer p=i%DatosEjercicio3.getNumProductos();
		return DatosEjercicio3.getCantidadDisponible(p)+1;
	}


	public Integer min(Integer i) {
		return 0;
	}
	

	public Integer size() {
		return DatosEjercicio3.getNumProductos()*DatosEjercicio3.getNumDestinos();
	}
	
	public Double errorDemanda(List<Integer> ls) {
		Double errorDemanda=0.;
		List<Integer> cantidadesDestinos= new ArrayList<Integer>();
		DatosEjercicio3.destinos.stream().forEach(x->cantidadesDestinos.add(0));
		for(int i = 0; i < size(); i++) {
			Integer p=i%DatosEjercicio3.getNumProductos();
			Integer j=i/DatosEjercicio3.getNumProductos();
			cantidadesDestinos.set(j, cantidadesDestinos.get(j)+ls.get(i));
		}
		
		for(int j=0;j<DatosEjercicio3.getNumDestinos();j++) {
			if(cantidadesDestinos.get(j)<DatosEjercicio3.getDemandaMinima(j)) {
				errorDemanda+= Math.pow(DatosEjercicio3.getDemandaMinima(j)-cantidadesDestinos.get(j),2);
			}
		}
		return errorDemanda;
	}
	
	public Double errorCantidad(List<Integer> ls) {
		Double errorCantidad=0.;
		List<Integer> cantidadesProdcutos= new ArrayList<Integer>();
		DatosEjercicio3.productos.stream().forEach(x->cantidadesProdcutos.add(0));
		for(int i = 0; i < size(); i++) {
			Integer p=i%DatosEjercicio3.getNumProductos();
			Integer j=i/DatosEjercicio3.getNumProductos();
			cantidadesProdcutos.set(p, cantidadesProdcutos.get(p)+ls.get(i));
		}
		
		for(int i=0;i<DatosEjercicio3.getNumProductos();i++) {
			if(cantidadesProdcutos.get(i)>DatosEjercicio3.getCantidadDisponible(i)) {
				errorCantidad+=Math.pow(cantidadesProdcutos.get(i)-DatosEjercicio3.getCantidadDisponible(i), 2);
			}
		}
		return errorCantidad;
	}
	

	public Double fitnessFunction(List<Integer> ls) {
		Double goal=0.;
		Double errorDemanda=errorDemanda(ls);
		Double errorCantidad=errorCantidad(ls);
		for(int i = 0; i < size(); i++) {
				Integer p=i%DatosEjercicio3.getNumProductos();
				Integer j=i/DatosEjercicio3.getNumProductos();
				goal+=ls.get(i)*DatosEjercicio3.getCosteAlmacenamiento(p, j);
		}

		
		Double fitness=-goal-1.0*errorCantidad-10*errorDemanda;
		
		return fitness;
	}
	
	public Ejercicio3Solucion solucion(List<Integer> ls) {
		return Ejercicio3Solucion.create(ls);
	}

}
