package org.palladiosimulator.editors.usage.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.ui.dialog.OpenStoExDialog;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class LoopIterationsAction extends OpenStoExDialog {

	public LoopIterationsAction() {
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		Loop l = (Loop) element;
		return l.getLoopIteration_Loop();
	}

	@Override
	protected TypeEnum getExpectedType(RandomVariable rv) {
		return TypeEnum.INT;
	}

}
