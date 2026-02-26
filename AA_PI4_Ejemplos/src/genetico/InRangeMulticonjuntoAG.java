package genetico;

import java.util.List;

import datos.DatosMulticonjunto;
import soluciones.SolucionMulticonjunto;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeMulticonjuntoAG implements ValuesInRangeData<Integer, SolucionMulticonjunto> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		AuxiliaryAg.distanceToEqZero(sum-DatosMulticonjunto.getSuma());
		return null;
	}

	@Override
	public SolucionMulticonjunto solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return null;
	}

}
