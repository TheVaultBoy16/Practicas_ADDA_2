package datos;

import java.util.ArrayList;
import java.util.List;


import us.lsi.common.Files2;



public class DatosEjercicio2 {
	
	public static void main(String[] args) {
		//Descomentar y comentar para probar con los distintos ficheros
		iniDatos("ficheros/Ejercicio2DatosEntrada1.txt");
//		iniDatos("ficheros/Ejercicio2DatosEntrada2.txt");
//		iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");
		List<Producto> lProductos= new ArrayList<Producto>(productos);
		for(Producto p:lProductos) {
			System.out.println(p);
		}
		System.out.println("\nLista de Categorias:"+categorias);
		System.out.println("\nPresupuesto: "+presupuesto);
	}
	
	public static record Producto(Integer id_prod,Integer precio,Integer categoria,Integer valoracion) {
		public static Producto create(String s) {
			String[] v=s.split(":");
			Integer id=Integer.parseInt(v[0].trim());
			Integer precio=Integer.parseInt(v[1].trim());
			Integer categoria=Integer.parseInt(v[2].trim());
			Integer valoracion=Integer.parseInt(v[3].trim());
			return new Producto(id, precio, categoria, valoracion);
		}
	}
	
	public static List<Producto> productos;
	public static List<Integer> categorias;
	public static Integer presupuesto;
	
	public static void iniDatos(String fichero) {
		List<String> lineas=Files2.linesFromFile(fichero);
		productos= new ArrayList<>();
		categorias= new ArrayList<>();
		presupuesto=0;
		
		for(String linea:lineas) {
			if(!linea.contains("//")) {
				if(linea.contains("P")) {
					presupuesto=Integer.parseInt(linea.replace("Presupuesto =", "").trim());
				}else {
					productos.add(Producto.create(linea));
					categorias.add(Producto.create(linea).categoria());
				}
			}
		}
		
	}
	
	
	public static List<Producto> getProductos(){
		return productos;
	}
	
	public static List<Integer> getCategorias(){
		return categorias;
	}
	
	public static Producto getProducto(Integer i) {
		return productos.get(i);
	}

	public static Integer getPresupuesto(){//pres
		return presupuesto;
	}
	
	public static Integer getNumProductos() {//n
		return productos.size();
	}
	public static Integer getNumCategorias() {//m
		return categorias.size();
	}
	
	
	public static Integer getCategoria(Integer i,Integer j) {//cij
		Integer res=0;
		if(productos.get(i).categoria()==categorias.get(j)) {
			res=1;
		}
		return res;
	}
	
	public static Integer getPrecio(Integer i) {//pi
		return productos.get(i).precio();
	}
	
	public static Integer getValoracion(Integer i) {//vi
		return productos.get(i).valoracion();
	}
	public static Integer getCategoriaDe(Integer i) {
		return productos.get(i).categoria();
	}
	

	
	
	

}
