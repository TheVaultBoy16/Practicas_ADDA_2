package ejercicio2;

import java.util.List;
import java.util.Locale;


import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio2AG {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 100;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		Ejercicio2AG p = new Ejercicio2AG("ficheros/Ejercicio2DatosEntrada1.txt");
//		Ejercicio2AG p = new Ejercicio2AG("ficheros/Ejercicio2DatosEntrada2.txt");
//		Ejercicio2AG p = new Ejercicio2AG("ficheros/Ejercicio2DatosEntrada3.txt");
		
		
		AlgoritmoAG<List<Integer>, Ejercicio2Solucion> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
		

	}

}
