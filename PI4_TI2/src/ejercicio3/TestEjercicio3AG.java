package ejercicio3;

import java.util.List;
import java.util.Locale;


import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEjercicio3AG {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		//Descomentar y comentar para probar con los distintos ficheros
		
		Ejercicio3AG p = new Ejercicio3AG("ficheros/Ejercicio3DatosEntrada1.txt");
//		Ejercicio3AG p = new Ejercicio3AG("ficheros/Ejercicio3DatosEntrada2.txt");
//		Ejercicio3AG p = new Ejercicio3AG("ficheros/Ejercicio3DatosEntrada3.txt"); // Ejecutar este varias veces para obtener el resultado
		
		
		AlgoritmoAG<List<Integer>, Ejercicio3Solucion> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");

	}

}
