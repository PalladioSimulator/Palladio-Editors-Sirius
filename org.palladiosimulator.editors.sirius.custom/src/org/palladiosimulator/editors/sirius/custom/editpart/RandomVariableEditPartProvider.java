package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListElementSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.resourceenvironment.impl.ProcessingResourceSpecificationImpl;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

@SuppressWarnings("restriction")
public class RandomVariableEditPartProvider extends AbstractEditPartProvider {

	class DirectEditForRandomVariable extends LabelDirectEditPolicy {

		@Override
		protected org.eclipse.gef.commands.Command getDirectEditCommand(DirectEditRequest edit) {
			final EObject element = ((org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart) getHost())
					.resolveSemanticElement();
			final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(element);
			final String labelText = (String) edit.getCellEditor().getValue();
			final RecordingCommand cmd = new RecordingCommand(domain) {

				@Override
				protected void doExecute() {
					if (element instanceof DSemanticDecorator) {
						final EObject target = ((DSemanticDecorator) element).getTarget();
						System.out.println("DirectEdit succesfull");
						// TODO save result to the model (as in PCMServices)
					}
				}
			};
			return new ICommandProxy(new GMFCommandWrapper(domain, cmd));

		}

	}

	/**
	 * @generated
	 */
	@Override
	public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
		if (view.getElement() instanceof DNodeListElementSpec) {
			DNodeListElementSpec element = (DNodeListElementSpec) view.getElement();
			String name = element.getMapping().getName();

			final RandomVariableEditPart editPart = new RandomVariableEditPart(view);

			switch (name) {
			case "thinkTime": // Closed Workload Think Time Action
				editPart.setExpectedType(TypeEnum.DOUBLE);
				return editPart;
			case "delayTime": // Delay Time Action
				editPart.setExpectedType(TypeEnum.DOUBLE);
				return editPart;
			case "GuardedBranchTransition": // Guarded Branch Transistion Condition Dialog
				editPart.setExpectedType(TypeEnum.BOOL);
				return editPart;
			case "Condition": // Guarded Branch Transistion Condition Dialog
				editPart.setExpectedType(TypeEnum.BOOL);
				return editPart;
			case "Loop": // Loop Iteration
				editPart.setExpectedType(TypeEnum.INT);
				return editPart;
			case "LoopIterationCount": // Loop Iteration
				editPart.setExpectedType(TypeEnum.INT);
				return editPart;
			case "loop": // LoopIterationsAction; is mapped to LoopIterationCount, too
							// (usage.odesign)
				editPart.setExpectedType(TypeEnum.INT);
				return editPart;
			case "PassiveResource": // SetCapacity
				editPart.setExpectedType(TypeEnum.DOUBLE);
				return editPart;
			case "InfrastructureCall": // NumberOfCalls
				editPart.setExpectedType(TypeEnum.INT);
				return editPart;
			}
			return editPart;
		}
		return null;

	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			final View view = ((IEditPartOperation) operation).getView();
			if (view.getElement() instanceof DNodeListElementSpec) {
				DNodeListElementSpec element = (DNodeListElementSpec) view.getElement();
				String name = element.getMapping().getName();
				if (SiriusVisualIDRegistry.getVisualID(view) == DNodeListElementEditPart.VISUAL_ID) {
					switch (name) {
					case "Processing Rate":
						return true; // any
					case "Write Processing Rate":
						return true; // any
					case "Read Processing Rate":
						return true; // any
					case "thinkTime": // Closed Workload Think Time Action
						return true; // double
					case "delayTime": // Delay Time Action
						return true; // double
					case "GuardedBranchTransition": // Guarded Branch Transistion Condition Dialog
						return true; // bool
					case "Condition": // Guarded Branch Transistion Condition Dialog
						return true; // bool
					case "Loop": // Loop Iteration
						return true; // int
					case "LoopIterationCount": // Loop Iteration
						return true;
					case "loop": // LoopIterationsAction; is mapped to LoopIterationCount, too
									// (usage.odesign)
						return true;
					case "interArrivalTime": // OpenWorkloadInterArrivalTimeAction
						return true; // any
					case "PassiveResource": // SetCapacity
						return true; // double
					case "Latency": // Set Linking Resource Latency
						return true; // any
					case "Throughput": // Set Linking Resource Throughput
						return true; // any
					case "InfrastructureCall": // NumberOfCalls
						return true; // int
					case "ResourceDemand": // SetSpecification
						return true; // any
					case "ResourceCall": // SetSpecification, SetNumberOfCalls (int as expected type)
						return true; // any
					case "SSET": // SetSSETSpecification (NodeCreation)
						return true; // any
					case "Variable Characterisation": // Set Variable Characterisation Specification
						return true; // any
					case "variableCharacterisation": // Set Variable Characterisation Specification
						return true; // any
					case "VariableCharacterisationInput":
						return true;
					case "VariableCharacterisationOutput":
						return true;
					case "VariableCharacterisationvariableSetter":
						return true;
					}

				}
			}

		}
		return false;

	}
}