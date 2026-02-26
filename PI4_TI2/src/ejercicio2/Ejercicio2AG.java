	package ejercicio2;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


import datos.DatosEjercicio2;
import us.lsi.ag.BinaryData;

public class Ejercicio2AG implements BinaryData<Ejercicio2Solucion> {
	
	public Ejercicio2AG(String linea) {
		DatosEjercicio2.iniDatos(linea);
	}


	public Integer size() {
		return DatosEjercicio2.getNumProductos();
	}
	
	public Double errorCategorias(List<Integer> ls) {
		Set<Integer> categorias= new HashSet<Integer>();
		Double error=0.;
		for(int i=0; i<ls.size();i++) {
			if(ls.get(i)>0) {
				categorias.add(DatosEjercicio2.getCategoriaDe(i));
			}
		}
		if(categorias.size()<DatosEjercicio2.getNumCategorias()) {
			error+=(DatosEjercicio2.getNumCategorias()-categorias.size());
		}
		return error;
	}
	
	public Double errorPresupuesto(List<Integer> ls) {
		Set<Integer> categorias= new HashSet<Integer>();
		Double sum=0.;
		Double error=0.;
		Integer presupuesto=DatosEjercicio2.getPresupuesto();
		for(int i=0; i<ls.size();i++) {
			if(ls.get(i)>0) {
				categorias.add(DatosEjercicio2.getCategoriaDe(i));
			}
		}
		for(Integer c:DatosEjercicio2.categorias) {
			for(int i=0; i<ls.size();i++) {
				if(DatosEjercicio2.getCategoria(c, i)==1) {
					sum+=DatosEjercicio2.getPrecio(i);
				}
			}
		}
		if(sum>presupuesto) {		
			error+=sum-presupuesto;
		}
		return error;
	}
	
	public Double errorMedia(List<Integer> ls) {
		Double sumaValoracion=0.;
		Integer num=0;
		Double media=0.;
		Double error=0.;
		for(int i=0; i<ls.size();i++) {
			if(ls.get(i)>0) {
				sumaValoracion+=DatosEjercicio2.getValoracion(i);
				num++;
			}
		}
		
		media=sumaValoracion/num;
		if(media<3) {
			error+=(3-media);
		}
		return error;
	}


	public Double fitnessFunction(List<Integer> ls) {
		Double goal=0.;
		Double error1=errorCategorias(ls);
		Double error2=errorPresupuesto(ls);
		Double error3=errorMedia(ls);
		
		for(int i=0; i<ls.size();i++) {
			if(ls.get(i)>0) {
				goal+=ls.get(i)*DatosEjercicio2.getPrecio(i);
			}
		}
		Double fitness=-goal-1000.0*(error1+error2+error3);
		return fitness;
	}


	public Ejercicio2Solucion solucion(List<Integer> ls) {
		Ejercicio2Solucion s = Ejercicio2Solucion.create(ls);
		return s;
	}
	
}
