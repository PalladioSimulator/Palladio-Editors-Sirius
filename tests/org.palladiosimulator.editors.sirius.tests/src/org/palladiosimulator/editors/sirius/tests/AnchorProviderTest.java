package org.palladiosimulator.editors.sirius.tests;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
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
	
	private static final double EPSILON = 0.01;
	private static final double RADIUS_MARGIN = 5;

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
	
	
	
	private class ConnectionInitializer {

		private Point srcAnchorPoint, srcEdgeEndPoint, targetAnchorPoint, targetEdgeEndPoint;
		
		private String nameOfEdgeCreationTool;
		
		private DDiagramElement srcNode, targetNode;
		private NodeEditPart srcNodeEP, targetNodeEP;
		private Rectangle srcNodeRect, targetNodeRect;
		
		private DEdge dEdge;
		private Connection connection;
		
		public ConnectionInitializer (String srcName, String targetName, String nameOfEdgeCreationTool) {
			this.nameOfEdgeCreationTool = nameOfEdgeCreationTool;
			initElements(srcName, targetName);
			initAnchorPoints();
			createEdge();
			initEdgeEndPoints();
			compareAnchorWithEdgeEndPoints();
		}
		
		private void initElements (String srcName, String targetName) {
			// Source Node
			srcNode = getDiagramElementsFromLabel(diagram, srcName).get(0);
			srcNodeEP = (NodeEditPart) getEditPart(srcNode);
			srcNodeRect = getEditPart(srcNode).getFigure().getBounds();

			// Target Node
			targetNode = getDiagramElementsFromLabel(diagram, targetName).get(0);
			targetNodeEP = (NodeEditPart) getEditPart(targetNode);
			targetNodeRect = getEditPart(targetNode).getFigure().getBounds();
		}
		
		private void initAnchorPoints () {
			srcAnchorPoint = srcNodeEP.getSourceConnectionAnchor(cursor).getLocation(new Point(0, 0));
			targetAnchorPoint = targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(srcAnchorPoint);
		}
		
		private void createEdge() {
			assertEquals(0, diagram.getEdges().size());
			applyEdgeCreationTool(this.nameOfEdgeCreationTool, diagram, (EdgeTarget) srcNode, (EdgeTarget) targetNode);
			dEdge = diagram.getEdges().get(0);
			assertNotNull(dEdge);
			connection = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();
		}
		
		private void initEdgeEndPoints () {
			srcEdgeEndPoint = connection.getPoints().getFirstPoint();
			targetEdgeEndPoint = connection.getPoints().getLastPoint();
		}
		
		private void compareAnchorWithEdgeEndPoints() {
			assertEquals("Source of " + nameOfEdgeCreationTool + ": The Edges Endpoint differs from the Anchor Point location", srcAnchorPoint, srcEdgeEndPoint);
			assertEquals("Target of " + nameOfEdgeCreationTool + ": The Edges Endpoint differs from the Anchor Point location", targetAnchorPoint, targetEdgeEndPoint);
		}
		
		public void assertSrcAnchorPointEquals(Point desiredLocation) {
			assertEquals("Source of " + nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location", srcAnchorPoint, desiredLocation);
		}
		
		public void assertTargetAnchorPointEquals(Point desiredLocation) {
			assertEquals("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location", targetAnchorPoint, desiredLocation);
		}
		
		public void assertConnectionAimsAt(Point desiredLocation) {
			assertTrue(nameOfEdgeCreationTool + ": The connection doesn't aim at the desired location",
					isStraightLine(srcAnchorPoint, targetAnchorPoint, desiredLocation));
		}
		
//		public void assertSrcAnchorPointOnLine(Point a, Point b) {
//			assertTrue("Source of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified straight line",
//					isStraightLine(a, srcAnchorPoint, b));
//		}
		
		public void assertTargetAnchorPointOnLine(Point a, Point b) {
			assertTrue("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified straight line",
					isStraightLine(a, targetAnchorPoint, b));
		}
		
		public void assertTargetAnchorPointOnCircleAround(Point desiredLocation, double radius) {
			assertTrue("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified circle line",
					Math.abs(radius - targetAnchorPoint.getDistance(desiredLocation)) <= RADIUS_MARGIN);
		}
		
		public void deleteEdge() {
			// Edge deletion, cleaning for next test
			applyDeletionTool(dEdge);
			assertEquals(0, diagram.getEdges().size());
		}
		
		public DDiagramElement getSrcNode() {
			return this.srcNode;
		}

		public DDiagramElement getTargetNode() {
			return this.targetNode;
		}

		public Rectangle getSrcNodeRect() {
			return this.srcNodeRect;
		}

		public Rectangle getTargetNodeRect() {
			return this.targetNodeRect;
		}
		
		private boolean isStraightLine(Point a, Point middle, Point b) {
			PrecisionPoint src = new PrecisionPoint(a);
			PrecisionPoint target1 = new PrecisionPoint(middle);
			PrecisionPoint target2 = new PrecisionPoint(b);
			
			final double distanceSrcTarget1 = src.getDistance(target1);
			Point unitVectorTowardsTarget1 = target1.getTranslated(src.getNegated()).getScaled(1 / distanceSrcTarget1);
			final double distanceSrcTarget2 = src.getDistance(target2);
			Point unitVectorTowardsTarget2 = target2.getTranslated(src.getNegated()).getScaled(1 / distanceSrcTarget2);
			
			final double distanceOfUnitVectors = unitVectorTowardsTarget1.getDistance(unitVectorTowardsTarget2);
			return distanceOfUnitVectors < EPSILON;
		}
	}

	
	
	@Test
	public void testAssemblyConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("operationRequiredRoleNode", "operationProvidedRoleNode", "AssemblyConnector");
		
		// Calculate desired Values and compare Values
		
		// 1st: OperationRequiredRoleNode --> Top Center and 0.25 * length down
		Point srcDesiredPoint = cInit.getSrcNodeRect().getTop().translate(0, 7.5);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredPoint);
		cInit.assertSrcAnchorPointEquals(srcDesiredPoint);
		
		// TODO pixel bei 30x30 messen und bessere werte für punkt, radius und margin
		// 2nd: OperationProvidedRoleNode --> Bottom Center and 10 pixel up
		Point targetDesiredPoint = cInit.getTargetNodeRect().getBottom().translate(0, -10);
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredPoint);
		double radius = 7.0;
		cInit.assertTargetAnchorPointOnCircleAround(targetDesiredPoint, radius);
		
		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}
	
	
	
	@Test
	public void testSourceDelegationConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "externalSourceRoleNode", "SourceDelegationConnector");
				
		// Calculate desired Values and compare Values
		
		// 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
		Point srcDesiredPoint = cInit.getSrcNodeRect().getBottom().translate(0, -12);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredPoint);
		cInit.assertSrcAnchorPointEquals(srcDesiredPoint);
		
		// 2nd: ExternalSourceRoleNode --> Left Center (because external role)
		Point targetDesiredPoint = cInit.getTargetNodeRect().getLeft();
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredPoint);
		cInit.assertTargetAnchorPointEquals(targetDesiredPoint);
		
		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}
	
	
	
	@Test
	public void testEventChannelSourceConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "<<EventChannel>>\n" + "eventChannel", "EventChannelSourceConnector");
		
		// Calculate desired Values and compare Values
		
		// 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
		Point srcDesiredPoint = cInit.getSrcNodeRect().getBottom().translate(0, -12);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredPoint);
		cInit.assertSrcAnchorPointEquals(srcDesiredPoint);
		
		// 2nd: Target should be on Event Channel border, aiming towards the center
		Point targetDesiredCenterPoint = cInit.getTargetNodeRect().getCenter();
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredCenterPoint);
		cInit.assertConnectionAimsAt(targetDesiredCenterPoint);
		
		// 3rd: If target is on the border, it is between corner points of the diamond shape
		Point topOfDiamondShape = cInit.getTargetNodeRect().getTop();
		Point leftOfDiamondShape = cInit.getTargetNodeRect().getLeft();
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(topOfDiamondShape);
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(leftOfDiamondShape);
		cInit.assertTargetAnchorPointOnLine(leftOfDiamondShape, topOfDiamondShape);
		
		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}
}