package org.palladiosimulator.editors.sirius.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.EditorPart;
import org.junit.Ignore;
import org.junit.Test;

public class AnchorProviderTest extends SiriusDiagramTestCase {

	/**
	 * Path to the test model folder
	 */
	private static final String TEST_MODEL_PATH = "/org.palladiosimulator.editors.sirius.tests/testmodels/anchorProviderTest/";
	/**
	 * Append name of the .system model
	 */
	private static final String SEMANTIC_MODEL_PATH = TEST_MODEL_PATH + "MySystem.system";

	/**
	 * Append name of the .aird file
	 */
	private static final String REPRESENTATION_MODEL_PATH = TEST_MODEL_PATH + "representations.aird";

	/**
	 * Path to the .odesign of the model which is under observation.
	 */
	private static final String MODELER_PATH = "/org.palladiosimulator.editors.sirius.assembly/description/assembly.odesign";

	private static final String REPRESENTATION_DESC_NAME = "Assembly Diagram";

	private static final String VIEWPOINT_NAME = "Assembly";

	private DDiagram diagram;

	private IEditorPart editorPart;

	private LocationRequest cursor;

	@Override
	protected void setUp() throws Exception {

		super.setUp();

		genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
		initViewpoint(VIEWPOINT_NAME);
		diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];

		assertNotNull(diagram);

		// Set up Default Cursor
		cursor = new LocationRequest();
		cursor.setLocation(new Point(0, 0));

		editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());

		TestsUtil.synchronizationWithUIThread();
	}

	@Override
	protected void tearDown() throws Exception {
		DialectUIManager.INSTANCE.closeEditor(editorPart, false);
		TestsUtil.synchronizationWithUIThread();
		diagram = null;
		editorPart = null;
		super.tearDown();
	}

	// @Test
	public void testAssemblyConnectorPointsAfterEdgeCreation() {

		Point reqDesiredPoint, reqAnchorPoint, reqEdgeEndPoint;
		Point provDesiredPoint, provAnchorPoint, provEdgeEndPoint;

		// Source Node
		DDiagramElement reqNode = getDiagramElementsFromLabel(diagram, "operationRequiredRoleNode").get(0);
		NodeEditPart reqNodeEP = (NodeEditPart) getEditPart(reqNode);
		Rectangle reqNodeRect = getEditPart(reqNode).getFigure().getBounds();

		// Target Node
		DDiagramElement provNode = getDiagramElementsFromLabel(diagram, "operationProvidedRoleNode").get(0);
		NodeEditPart provNodeEP = (NodeEditPart) getEditPart(provNode);
		Rectangle provNodeRect = getEditPart(provNode).getFigure().getBounds();

		// Set the desired diagram points
		// Figures are 30x30 pixel long
		reqDesiredPoint = reqNodeRect.getTop().translate(0, 7.5); // --> Top Center and 0.25 * length down
		getEditPart(reqNode).getFigure().translateToAbsolute(reqDesiredPoint);
		provDesiredPoint = provNodeRect.getBottom().translate(0, -3); // --> Bottom Center and 3 pixel up
		getEditPart(provNode).getFigure().translateToAbsolute(provDesiredPoint);

		// Edge creation
		assertEquals(0, diagram.getEdges().size());
		super.applyEdgeCreationTool("AssemblyConnector", diagram, (EdgeTarget) reqNode, (EdgeTarget) provNode);
		DEdge dEdge = diagram.getEdges().get(0);
		assertNotNull(dEdge);
		Connection connection = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();

		// Compare Values
		reqAnchorPoint = reqNodeEP.getSourceConnectionAnchor(cursor).getLocation(new Point(0, 0));
		assertEquals("The AnchorPoint differs from its desired location", reqDesiredPoint, reqAnchorPoint);
		reqEdgeEndPoint = connection.getPoints().getFirstPoint();
		assertEquals("The Edges Endpoint differs from its desired location", reqDesiredPoint, reqEdgeEndPoint);

		provAnchorPoint = provNodeEP.getTargetConnectionAnchor(cursor).getLocation(reqDesiredPoint);
		assertEquals("The AnchorPoint differs from its desired location", provDesiredPoint, provAnchorPoint);
		provEdgeEndPoint = connection.getPoints().getLastPoint();
		assertEquals("The Edges Endpoint differs from its desired location", provDesiredPoint, provEdgeEndPoint);

		// Edge deletion, cleaning for next test
		super.applyDeletionTool(dEdge);
		assertEquals(0, diagram.getEdges().size());
	}

	// @Test
	public void testSourceDelegationConnectorPointsAfterEdgeCreation() {

		Point srcDesiredPoint, srcAnchorPoint, srcEdgeEndPoint;
		Point targetDesiredPoint, targetAnchorPoint, targetEdgeEndPoint;

		// Source Node
		DDiagramElement srcNode = getDiagramElementsFromLabel(diagram, "sourceRoleNode").get(0);
		NodeEditPart srcNodeEP = (NodeEditPart) getEditPart(srcNode);
		Rectangle srcNodeRect = getEditPart(srcNode).getFigure().getBounds();

		// Target Node
		DDiagramElement targetNode = getDiagramElementsFromLabel(diagram, "externalSourceRoleNode").get(0);
		NodeEditPart targetNodeEP = (NodeEditPart) getEditPart(targetNode);
		Rectangle targetNodeRect = getEditPart(targetNode).getFigure().getBounds();

		// Set the desired diagram points
		// Figures are 30x30 pixel long
		srcDesiredPoint = srcNodeRect.getBottom().translate(0, -12); // --> Bottom Center and 0.4 * length up
		getEditPart(srcNode).getFigure().translateToAbsolute(srcDesiredPoint);
		targetDesiredPoint = targetNodeRect.getLeft(); // --> Left Center (because external role)
		getEditPart(targetNode).getFigure().translateToAbsolute(targetDesiredPoint);

		// Edge creation
		assertEquals(0, diagram.getEdges().size());
		super.applyEdgeCreationTool("SourceDelegationConnector", diagram, (EdgeTarget) srcNode,
				(EdgeTarget) targetNode);
		DEdge dEdge = diagram.getEdges().get(0);
		assertNotNull(dEdge);
		Connection connection = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();

		// Compare Values
		srcAnchorPoint = srcNodeEP.getSourceConnectionAnchor(cursor).getLocation(new Point(0, 0));
		assertEquals("The AnchorPoint differs from its desired location", srcDesiredPoint, srcAnchorPoint);
		srcEdgeEndPoint = connection.getPoints().getFirstPoint();
		assertEquals("The Edges Endpoint differs from its desired location", srcDesiredPoint, srcEdgeEndPoint);

		targetAnchorPoint = targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(srcDesiredPoint);
		assertEquals("The AnchorPoint differs from its desired location", targetDesiredPoint, targetAnchorPoint);
		targetEdgeEndPoint = connection.getPoints().getLastPoint();
		assertEquals("The Edges Endpoint differs from its desired location", targetDesiredPoint, targetEdgeEndPoint);

		// Edge deletion, cleaning for next test
		super.applyDeletionTool(dEdge);
		assertEquals(0, diagram.getEdges().size());
	}

	@Test
	public void testEventChannelSourceConnectorPointsAfterEdgeCreation() {

		Point srcDesiredPoint, srcAnchorPoint, srcEdgeEndPoint;
		Point targetDesiredPoint, targetAnchorPoint, targetEdgeEndPoint;

		// Source Node
		DDiagramElement srcNode = getDiagramElementsFromLabel(diagram, "sourceRoleNode").get(0);
		NodeEditPart srcNodeEP = (NodeEditPart) getEditPart(srcNode);
		Rectangle srcNodeRect = getEditPart(srcNode).getFigure().getBounds();

		// Target Node
		DDiagramElement targetNode = getDiagramElementsFromLabel(diagram, "<<EventChannel>>\n" + "eventChannel").get(0);
		NodeEditPart targetNodeEP = (NodeEditPart) getEditPart(targetNode);
		Rectangle targetNodeRect = getEditPart(targetNode).getFigure().getBounds();

		// Set the desired diagram points
		// Figures are 30x30 pixel long
		srcDesiredPoint = srcNodeRect.getBottom().translate(0, -12); // --> Bottom Center and 0.4 * length up
		getEditPart(srcNode).getFigure().translateToAbsolute(srcDesiredPoint);
		targetDesiredPoint = targetNodeRect.getCenter(); // --> Center
		getEditPart(targetNode).getFigure().translateToAbsolute(targetDesiredPoint);
		cursor.setLocation(targetDesiredPoint);
		// editorPart.
		targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(targetDesiredPoint);

		// Edge creation
		assertEquals(0, diagram.getEdges().size());
		super.applyEdgeCreationTool("EventChannelSourceConnector", diagram, (EdgeTarget) srcNode,
				(EdgeTarget) targetNode);
		DEdge dEdge = diagram.getEdges().get(0);
		assertNotNull(dEdge);
		Connection connection = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();

		// Compare Values
		srcAnchorPoint = srcNodeEP.getSourceConnectionAnchor(cursor).getLocation(new Point(0, 0));
		assertEquals("The AnchorPoint differs from its desired location", srcDesiredPoint, srcAnchorPoint);
		srcEdgeEndPoint = connection.getPoints().getFirstPoint();
		assertEquals("The Edges Endpoint differs from its desired location", srcDesiredPoint, srcEdgeEndPoint);

		targetAnchorPoint = targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(targetDesiredPoint);
		assertEquals("The AnchorPoint differs from its desired location", targetDesiredPoint, targetAnchorPoint);
		targetEdgeEndPoint = connection.getPoints().getLastPoint();
		assertEquals("The Edges Endpoint differs from its desired location", targetDesiredPoint, targetEdgeEndPoint);

		// Edge deletion, cleaning for next test
		super.applyDeletionTool(dEdge);
		assertEquals(0, diagram.getEdges().size());
	}

//	@Override
//	protected void setUp() throws Exception {
//
//		System.out.println("");
//		System.out.println(
//				"--------------------------------------------------------------------------------------------");
//
//		super.setUp();
//
//		System.out.println("Creating session from: " + REPRESENTATION_MODEL_PATH);
//
//		genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
//
//		System.out.println("");
//		System.out.println("Model loaded: " + this.semanticModel.eContents());
//		System.out.println("Session: " + this.session);
//
//		initViewpoint(VIEWPOINT_NAME);
//
//		System.out.println("Viewpoint \"" + VIEWPOINT_NAME + "\": " + this.viewpoints);
//
//		diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
//
//		System.out.println("Diagram: \"" + REPRESENTATION_DESC_NAME + "\" loaded: " + diagram);
//		System.out.println(
//				"--------------------------------------------------------------------------------------------");
//
//		assertNotNull(diagram);
//		editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
//		TestsUtil.synchronizationWithUIThread();
//	}

//	@Test
//	public void test() {
//		// List<DDiagramElement> elements = diagram.getDiagramElements();
//		// diagram.getNodes().get(0);
////    	System.out.println("Number of Nodes: " + diagram.getNodes().size()); // Should be 2
////    	System.out.println("Node: " + diagram.getNodes().get(0)); // Provided Role
////    	
////    	DiagramElementMapping dElMap = diagram.getNodes().get(0).getDiagramElementMapping();
////    	
////    	System.out.println("getDiagramElementMapping: " + dElMap);
////    	
////    	System.out.println("");
////    	IGraphicalEditPart editPart = getEditPart(diagram.getNodes().get(0));
////    	System.out.println("getEditPart: " + editPart);
////
////    	Rectangle bounds = editPart.getFigure().getBounds();
////    	System.out.println("bounds: " + bounds);
////    	getDiagramElementsFromLabel(diagram, "operationRequiredRoleNode");
////    	System.out.println("Node: " + diagram.getNodes().get(0)); // Provided Role
//
//		LocationRequest lr = new LocationRequest();
//		lr.setLocation(new Point(0, 0));
//
//		// Source Node
//		DDiagramElement reqNode = getDiagramElementsFromLabel(diagram, "operationRequiredRoleNode").get(0);
//		NodeEditPart reqNodeEP = (NodeEditPart) getEditPart(reqNode);
//		Point reqNodePos = reqNodeEP.getSourceConnectionAnchor(lr).getLocation(new Point(0, 0));
//		Rectangle reqNodeRect = getEditPart(reqNode).getFigure().getBounds();
//		Point desiredValue = reqNodeRect.getTopLeft().translate(reqNodeRect.width() * 0.25, reqNodeRect.height() / 2);
//		getEditPart(reqNode).getFigure().translateToAbsolute(desiredValue);
//
//		System.out.println("Anchor of Source Node (req): " + reqNodeEP.getSourceConnectionAnchor(lr));
//		System.out.println("Position: " + reqNodePos);
//		System.out.println("Border Rect: " + reqNodeRect);
//		System.out.println("Desired Value: " + desiredValue);
//		System.out.println("");
//
//		// Target Node
//		DDiagramElement provNode = getDiagramElementsFromLabel(diagram, "operationProvidedRoleNode").get(0);
//		NodeEditPart provNodeEP = (NodeEditPart) getEditPart(provNode);
//		Point provNodePos = provNodeEP.getTargetConnectionAnchor(lr).getLocation(reqNodePos);
//
////        System.out.println("Anchor of Target Node (prov): " + provNodeEP.getSourceConnectionAnchor(lr));
////        System.out.println("Position: " + provNodePos);
////        System.out.println("");
//
//		// Edge creation
//		assertEquals(0, diagram.getEdges().size());
//		super.applyEdgeCreationTool("AssemblyConnector", diagram, (EdgeTarget) reqNode, (EdgeTarget) provNode);
//		DEdge dEdge = diagram.getEdges().get(0);
//		assertNotNull(dEdge);
//		ConnectionEditPart edge = (ConnectionEditPart) getEditPart(dEdge, editorPart);
//		System.out.println("Source Point: " + edge.getConnectionFigure().getSourceAnchor());
//
//		Connection connFigure = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();
//		System.out.println("Inspect Edge \"AssemblyConnector\": " + connFigure.getPoints().size() + ", "
//				+ connFigure.getPoints().getFirstPoint());
////        Point sourcePoint = connFigure.getSourceAnchor().getLocation(new Point(0,0));
////        Point targetPoint = connFigure.getTargetAnchor().getReferencePoint();
////        System.out.println("Inspect Edge \"AssemblyConnector\":");
////        System.out.println("Source Point: " + sourcePoint + ", Target Point: " + targetPoint);
//
////    	// get required node
////    	List<DDiagramElement> reqNodes = getDiagramElementsFromLabel(diagram, "operationRequiredRoleNode");
////        assertFalse("No diagram element found with the label : " + "operationRequiredRoleNode", reqNodes.isEmpty());
////        DDiagramElement reqNode = reqNodes.get(0);
////        IGraphicalEditPart class1EP = getEditPart(reqNode);
////        assertNotNull("No graphical Edit Part found with the Label" + "operationRequiredRoleNode", class1EP);
////        System.out.println("Inspect Node \"operationRequiredRoleNode\": " + class1EP);
////        
////        NodeEditPart nep = (NodeEditPart) class1EP;
////        LocationRequest lr = new LocationRequest();
////        lr.setLocation(new Point(0,0));
////        System.out.println("getSourceConnectionAnchor() of operationRequiredRoleNode: " + nep.getSourceConnectionAnchor(lr)); // same as getTargetConnectionAnchor()...
////        System.out.println("SourceAnchor Position: " + nep.getSourceConnectionAnchor(lr).getLocation(new Point(0,0))); // Punkt egal weil fixpunktanker
////        System.out.println("");
////
////        DDiagramElement provNode = getDiagramElementsFromLabel(diagram, "operationProvidedRoleNode").get(0);
////        NodeEditPart oProvRNEP = (NodeEditPart) getEditPart(provNode);
////        System.out.println("getSourceConnectionAnchor() of operationProvidedRoleNode: " + oProvRNEP.getSourceConnectionAnchor(lr));
////        System.out.println("SourceAnchor Position: " + oProvRNEP.getSourceConnectionAnchor(lr).getLocation(new Point(80,252))); // Ankerpunkt des req node
////        System.out.println("");
//
////        super.applyEdgeCreationTool("AssemblyConnector", diagram, (EdgeTarget) reqNode, (EdgeTarget) provNode);
////    	
////    	DEdge dEdge = diagram.getEdges().get(0);
////        assertNotNull(dEdge);
////        
////        System.out.println("Inspect Edge: " + dEdge);
////        IGraphicalEditPart editPart = getEditPart(dEdge);
////        ConnectionEditPart conn = (ConnectionEditPart) editPart;
////        conn.refresh();
////        Connection connFigure = conn.getConnectionFigure();
////        Point sourcePoint = connFigure.getSourceAnchor().getReferencePoint();
////        Point targetPoint = connFigure.getTargetAnchor().getReferencePoint();
////        System.out.println("Source Point: " + sourcePoint);
////        System.out.println("Target Point: " + targetPoint);
//
////        List<DDiagramElement> class2 = getDiagramElementsFromLabel(diagram, "assemblyConnector");
////        assertFalse("No diagram element found with the label : " + "assemblyConnector", class2.isEmpty());
////        DDiagramElement diagramElementClass2 = class2.get(0);
////        IGraphicalEditPart class2EP = getEditPart(diagramElementClass2);
////        assertNotNull("No graphical Edit Part found with the Label" + "assemblyConnector", class2EP);
////        System.out.println("Node: " + class2EP);
//	}

//	
//	@Test
//    public void test() {
//		final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
//        TestsUtil.synchronizationWithUIThread();
//
//        assertTrue(diagram.getOwnedDiagramElements().isEmpty());
//    }
}

//You can have your JUnit classes extend from SiriusDiagramTestCase, which adds lots of convenience (including data members such as semanticModel and session).
//We usually use SiriusDiagramTestCase#genericSetup() to set up a session with a model, an .odesign and a .aird file, then go from there.
//Diagrams from the .aird file can be obtained through getRepresentations(...).
//Once you have the diagram, you can get nodes and edges, etcetera.
//Depending on the test, it may sometimes be necessary to have an open editor for a diagram as well, e.g. using DialectUIManager.INSTANCE.openEditor(...).
//
//SiriusDiagramTestCase has a lot of methods for applying tools (such as the ones shown in the palette of your Sirius-based editor,
//as defined in the .odesign) to manipulate the model. This way, you can e.g. check whether a NodeCreationTool that you specified works as intended.
//
//The API of SiriusDiagramTestCase, combined with the documentation that gives the basics and core classes of programatically using Sirius
//(see https://www.eclipse.org/sirius/doc/developer/Architecture.html), can get you a long way.
//Still, there's plenty to figure out by yourself, so it'll take time, especially for the first couple of tests.
//
//Also, there are test support APIs in org.eclipse.sirius.tests.support.api.
//The main use we've gotten out of that is using org.eclipse.sirius.tests.support.api.TestsUtil.waitUntil(...)
//to have our tests wait for asynchronous activities to complete.
