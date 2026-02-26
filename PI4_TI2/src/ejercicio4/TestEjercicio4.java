package ejercicio4;

import java.util.List;
import java.util.Locale;


import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio4 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 5000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		Ejercicio4AG p = new Ejercicio4AG("ficheros/Ejercicio4DatosEntrada1.txt");
//		Ejercicio4AG p = new Ejercicio4AG("ficheros/Ejercicio4DatosEntrada2.txt");
//		Ejercicio4AG p = new Ejercicio4AG("ficheros/Ejercicio4DatosEntrada3.txt");
		
		
		AlgoritmoAG<List<Integer>,Ejercicio4Solucion> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");

	}
	
	

}
