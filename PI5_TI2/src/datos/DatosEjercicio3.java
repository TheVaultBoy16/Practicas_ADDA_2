package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import us.lsi.common.Files2;


public class DatosEjercicio3 {
	
	public static void main(String[] args) {
		//Descomentar y comentar para probar con los distintos ficheros
		iniDatos("ficheros/Ejercicio3DatosEntrada1.txt");
//		iniDatos("ficheros/Ejercicio3DatosEntrada2.txt");
//		iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
		List<Destino> lDestinos= new ArrayList<Destino>(destinos);
		List<Producto> lProductos= new ArrayList<Producto>(productos);
		for(Destino d:lDestinos) {
			System.out.println(d);
		}
		for(Producto p:lProductos) {
			System.out.println(p);
		}
	}

	
	public static record Destino(Integer id_destino,String nombre,Integer demandaMinima) {
		private static int id;
		
		public static Destino create(String s) {
			String [] v=s.split(":");
			String nombre=v[0].trim();
			String [] v2=v[1].split(";");
			Integer demandaMinima=Integer.parseInt(v2[0].replace("demandaminima=", "").trim());
			return new Destino(id++, nombre, demandaMinima);
		}
	}
	
	public static record Producto(Integer id_producto,String nombre,Integer cantidadDisponible,Map<Integer,Integer> costeAlmacenamiento) {
		private static int id;
		
		public static Producto create(String s) {
			String [] v=s.split("->");
			String nombre=v[0].trim();
			String [] v2=v[1].split(";");
			Integer cantidadDisponible=Integer.parseInt(v2[0].replace("uds=", "").trim());
			Map<Integer,Integer> costeAlmacenamiento= new HashMap<Integer, Integer>();
			String[] v3=v2[1].replace("coste_almacenamiento=", "").trim().split(",");
			for(String st:v3) {
				String[] v4=st.replace("(", "").replace(")", "").trim().split(":");
				costeAlmacenamiento.put(Integer.parseInt(v4[0].trim()), Integer.parseInt(v4[1].trim()));
			}
			return new Producto(id++, nombre, cantidadDisponible,costeAlmacenamiento);
		}
	}
	

	public static List<Destino> destinos;
	public static List<Producto> productos;
	
	public static void iniDatos(String fichero) {
		List<String> lineas=Files2.linesFromFile(fichero);
		destinos=new ArrayList<>();
		productos=new ArrayList<>();
		for(String linea:lineas) {
			if(!linea.contains("//")) {
				if(linea.contains("D") && !linea.contains("->")) {
					destinos.add(Destino.create(linea));
				}else if(linea.contains("P")) {
					productos.add(Producto.create(linea));
				}
			}
		}
	}
	
	
	public static List<Producto> getProductos(){
		return productos;
	}
	
	public static List<Destino> getDestinos(){
		return destinos;
	}
	
	public static Integer getNumProductos() {//n
		return productos.size();
	}
	public static Integer getNumDestinos() {//m
		return destinos.size();
	}
	
	public static Integer getCantidadDisponible(Integer i) {//Ci
		return productos.get(i).cantidadDisponible();
	}
	
	public static Integer getDemandaMinima(Integer j) {//Dj
		return destinos.get(j).demandaMinima();
	}
	
	public static Integer getCosteAlmacenamiento(Integer i,Integer j) {//Aij
		Integer res=0;
		Map<Integer,Integer> costeAlmacenamiento=productos.get(i).costeAlmacenamiento();
		if(costeAlmacenamiento.containsKey(j)) {
			res=costeAlmacenamiento.get(j);
		}
		return res;
	}
	
	public static List<Integer> listaUnidadesRestantes(){
		return productos.stream().map(x->x.cantidadDisponible()).toList();
		
	}
	
	public static List<Integer> listaDemandasRestantes(){
		return destinos.stream().map(x->x.demandaMinima()).toList();
	}


}
