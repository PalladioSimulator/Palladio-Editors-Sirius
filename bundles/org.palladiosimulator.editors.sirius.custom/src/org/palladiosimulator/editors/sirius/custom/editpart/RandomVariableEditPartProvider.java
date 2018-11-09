package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.impl.DDiagramElementImpl;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

@SuppressWarnings("restriction")
public class RandomVariableEditPartProvider extends AbstractEditPartProvider {

	class DirectEditLoopIterationCount extends LabelDirectEditPolicy {
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
						if (target instanceof LoopAction) {
							// in seff diagrams
							PCMRandomVariable randomVariable = ((LoopAction) target).getIterationCount_LoopAction();
							randomVariable.setSpecification(labelText);
						} else if (target instanceof Loop) {
							// in usage diagrams
							PCMRandomVariable randomVariable = ((Loop) target).getLoopIteration_Loop();
							randomVariable.setSpecification(labelText);
						}
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
		if (view.getElement() instanceof DDiagramElementImpl) {
			DDiagramElementImpl element = (DDiagramElementImpl) view.getElement();
			String name = element.getMapping().getName();
			switch (SiriusVisualIDRegistry.getVisualID(view)) {
			case DNodeListElementEditPart.VISUAL_ID:
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
				case "PassiveResource": // SetCapacity
					editPart.setExpectedType(TypeEnum.DOUBLE);
					return editPart;
				case "InfrastructureCall": // NumberOfCalls
					editPart.setExpectedType(TypeEnum.INT);
					return editPart;
				case "ResourceCall":
					editPart.setExpectedType(TypeEnum.INT);
					return editPart;
				case "Throughput":
				case "Latency": 
					editPart.setExpectedType(TypeEnum.DOUBLE);
					return editPart;
				}
				return editPart;
			case DNodeContainer2EditPart.VISUAL_ID: {
				if (name.equals("LoopIterationCount")) {
					RandomVariableNodeContainerEditPart containerEditPart = new RandomVariableNodeContainerEditPart(
							view, TypeEnum.INT);
					containerEditPart.installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
							new DirectEditLoopIterationCount());
					return containerEditPart;
				}
			}
			}
		}
		return null;

	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			final View view = ((IEditPartOperation) operation).getView();
			if (view.getElement() instanceof DDiagramElementImpl) {
				DDiagramElementImpl element = (DDiagramElementImpl) view.getElement();
				String name = element.getMapping().getName();

				if (SiriusVisualIDRegistry.getVisualID(view) == DNodeListElementEditPart.VISUAL_ID) {
					switch (name) {
					case "Processing Rate":
						return true;
					case "Write Processing Rate":
						return true;
					case "Read Processing Rate":
						return true;
					case "thinkTime":
						return true;
					case "delayTime":
						return true;
					case "GuardedBranchTransition":
						return true;
					case "Condition":
						return true;
					case "interArrivalTime":
						return true;
					case "PassiveResource":
						return true;
					case "Latency":
						return true;
					case "Throughput":
						return true;
					case "InfrastructureCall":
						return true;
					case "ResourceDemand":
						return true;
					case "ResourceCall":
						return true;
					case "SSET": 
						return true;
					case "Variable Characterisation":
						return true;
					case "variableCharacterisation":
						return true;
					case "VariableCharacterisationInput":
						return true;
					case "VariableCharacterisationOutput":
						return true;
					case "VariableCharacterisationvariableSetter":
						return true;
					}
				} else if (SiriusVisualIDRegistry.getVisualID(view) == DNodeContainer2EditPart.VISUAL_ID) {
					if (name.equals("LoopIterationCount"))
						return true;
				}
			}
		}
		return false;

	}
}