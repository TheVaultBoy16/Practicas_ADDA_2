package datos;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;

public class DatosMulticonjunto {
	
	public static Integer suma; // n
	public static List<Integer> numeros;
	

	
	public static Integer getSuma() {// n
		return suma;
	}
	
	public static List<Integer> getListaNumeros() {// i
		return numeros;
	}
	
	public static Integer getNumElementos() {// m
		return numeros.size();
	}
	
	public static Integer getElemento(Integer i) {// ei
		return numeros.get(i);
	}
	
	public static Integer getMultiplicidadMaxima(Integer i) {
		return suma/getElemento(i); // multiplicidad que la calculo yo, mi
	}
	
	public static void iniDatos(String fichero) {
		List<String> lineas=Files2.linesFromFile(fichero); // list string, yo espero solo una linia
		Preconditions.checkArgument(lineas.size()==1);
		String linea=lineas.get(0);
		
		String v[]=linea.split(":"); // array de String[]
		suma=Integer.parseInt(v[1]);
		numeros=List2.parse(v[0], ",", Integer::parseInt);// split de split
	}
	
	public static void main(String[] args) {
		iniDatos("ficheros/Ejemplo1DatosEntrada1.txt");
		System.out.println(suma+""+numeros);
	}

}
