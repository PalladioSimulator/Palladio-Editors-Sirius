package org.palladiosimulator.editors.seff.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.externaljavaactions.OpenStoExDialog;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ResourceDemandOpenStoexDialog extends OpenStoExDialog {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		ParametricResourceDemand rd = (ParametricResourceDemand) element;
		return rd.getSpecification_ParametericResourceDemand();
	}

}
