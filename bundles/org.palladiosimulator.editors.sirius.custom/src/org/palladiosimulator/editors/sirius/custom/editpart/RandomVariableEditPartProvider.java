package org.palladiosimulator.editors.sirius.custom.editpart;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.palladiosimulator.pcm.core.CorePackage;
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
				return new RandomVariableEditPart(view, getExpectedType(name));
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
	
	protected TypeEnum getExpectedType(String mappingName) {
	    TypeEnum expectedType = TypeEnum.ANY;
	    switch (mappingName) {
        case "GuardedBranchTransition": // Guarded Branch Transistion Condition Dialog
        case "Condition": // Guarded Branch Transistion Condition Dialog
            expectedType = TypeEnum.BOOL;
            break;
        case "InfrastructureCall": // NumberOfCalls
        case "ResourceCall":
            expectedType = TypeEnum.INT;
            break;
        case "thinkTime": // Closed Workload Think Time Action
        case "delayTime": // Delay Time Action
        case "Throughput":
        case "Latency":
        case "PassiveResource": // SetCapacity
            expectedType = TypeEnum.DOUBLE;
            break;
        }
	    return expectedType;
	}

	@Override
	public boolean provides(IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			final View view = ((IEditPartOperation) operation).getView();
			if (view.getElement() instanceof DDiagramElementImpl) {
				DDiagramElementImpl element = (DDiagramElementImpl) view.getElement();
				int visualID = SiriusVisualIDRegistry.getVisualID(view);

				if (visualID == DNodeListElementEditPart.VISUAL_ID && element.getDiagramElementMapping().getLabelDirectEdit() != null) {
					String inputExpr = element.getDiagramElementMapping().getLabelDirectEdit()
							.getInputLabelExpression();
					String[] splittedInputExpr = inputExpr.split("\\.");
					if (splittedInputExpr.length > 1) {
						String featureName = splittedInputExpr[1];
					for (EObject obj : element.getSemanticElements()) {
						EStructuralFeature rv = obj.eClass().getEStructuralFeature(featureName);
						if (rv != null) {
							EClassifier type = rv.getEType();
							if (type.equals(CorePackage.Literals.PCM_RANDOM_VARIABLE)) {
								return true;
							}
						}
					}}
				} else if (visualID == DNodeContainer2EditPart.VISUAL_ID) {
					String name = element.getMapping().getName();
					if (name.equals("LoopIterationCount")) {
						return true;
					}
				}

			}
		}
		return false;

	}
}