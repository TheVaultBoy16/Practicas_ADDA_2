package ple;

import java.io.IOException;

import datos.DatosMulticonjunto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejemplo1PLE {

	public static void main(String[] args) throws IOException {
		ejemplo1_model("ficheros/Ejemplo1DatosEntrada1.txt",1);

	}
	
	public static void ejemplo1_model(String fichero,Integer i) throws IOException {
		DatosMulticonjunto.iniDatos(fichero);
		
		AuxGrammar.generate(DatosMulticonjunto.class, "modelos/ejemplo1.lsi", "gurobi_models/ejemplo1-"+i+".lp");
		
		GurobiSolution solucion=GurobiLp.gurobi("gurobi_models/ejemplo1-"+i+".lp");
		
		System.out.println(solucion.toString((s,d) -> d > 0.));
		
	}

}
