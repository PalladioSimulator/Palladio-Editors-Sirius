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
	private final static int PIXEL_OFFSET = 2;

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

	/**
	 * Data Handler for Points and Diagram Elements of a Connection.
	 * <br>
	 * <br>
	 * 1) Use the constructor to create a connection edge. <br>
	 * 2) Use the assertion methods for checking values of the Points.
	 * <br>
	 * <br>
	 * 3) Use deleteEdge() for cleaning up the testmodel diagram for the next test.
	 * 
	 * @author Jonas Lehmann
	 */
	private class ConnectionInitializer {

		private Point srcAnchorPoint, srcEdgeEndPoint, targetAnchorPoint, targetEdgeEndPoint;

		private String nameOfEdgeCreationTool;

		private DDiagramElement srcNode, targetNode;
		private NodeEditPart srcNodeEP, targetNodeEP;
		private Rectangle srcNodeRect, targetNodeRect;

		private DEdge dEdge;
		private Connection connection;

		public ConnectionInitializer(String srcName, String targetName, String nameOfEdgeCreationTool) {
			// template method for all things to process after one another
			this.nameOfEdgeCreationTool = nameOfEdgeCreationTool;
			initElements(srcName, targetName);
			initAnchorPoints();
			createEdge();
			initEdgeEndPoints();
			compareAnchorWithEdgeEndPoints();
		}

		private void initElements(String srcName, String targetName) {
			// Source Node
			srcNode = getDiagramElementsFromLabel(diagram, srcName).get(0);
			srcNodeEP = (NodeEditPart) getEditPart(srcNode);
			srcNodeRect = getEditPart(srcNode).getFigure().getBounds();

			// Target Node
			targetNode = getDiagramElementsFromLabel(diagram, targetName).get(0);
			targetNodeEP = (NodeEditPart) getEditPart(targetNode);
			targetNodeRect = getEditPart(targetNode).getFigure().getBounds();
		}

		private void initAnchorPoints() {
			Point referencePointForSource = srcNodeEP.getSourceConnectionAnchor(cursor).getLocation(new PrecisionPoint(0, 0));
			Point referencePointForTarget = targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(new PrecisionPoint(0, 0));
			targetAnchorPoint = targetNodeEP.getTargetConnectionAnchor(cursor).getLocation(referencePointForSource);
			srcAnchorPoint = srcNodeEP.getSourceConnectionAnchor(cursor).getLocation(referencePointForTarget);
		}

		private void createEdge() {
			assertEquals(0, diagram.getEdges().size());
			applyEdgeCreationTool(this.nameOfEdgeCreationTool, diagram, (EdgeTarget) srcNode, (EdgeTarget) targetNode);
			dEdge = diagram.getEdges().get(0);
			assertNotNull(dEdge);
			connection = ((ConnectionEditPart) getEditPart(dEdge)).getConnectionFigure();
		}

		private void initEdgeEndPoints() {
			srcEdgeEndPoint = connection.getPoints().getFirstPoint();
			targetEdgeEndPoint = connection.getPoints().getLastPoint();
		}

		private void compareAnchorWithEdgeEndPoints() {
			System.out.println(nameOfEdgeCreationTool + ": srcA: " + srcAnchorPoint + ", srcE: " + srcEdgeEndPoint
					+ ", targetA: " + targetAnchorPoint + ", targetE: " + targetEdgeEndPoint);
			assertEquals(
					"Source of " + nameOfEdgeCreationTool
							+ ": The Edges Endpoint differs from the Anchor Point location",
					srcAnchorPoint, srcEdgeEndPoint);
			assertEquals(
					"Target of " + nameOfEdgeCreationTool
							+ ": The Edges Endpoint differs from the Anchor Point location",
					targetAnchorPoint, targetEdgeEndPoint);
		}

		public void assertSrcAnchorPointEquals(Point desiredLocation) {
			assertEquals("Source of " + nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
					srcAnchorPoint, desiredLocation);
		}

		public void assertTargetAnchorPointEquals(Point desiredLocation) {
			assertEquals("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
					targetAnchorPoint, desiredLocation);
		}
		
		public void assertTargetAnchorPointEqualsWithOffset(Point desiredLocation, double offset) {
			assertTrue("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
					Math.abs(offset - targetAnchorPoint.getDistance(desiredLocation)) <= EPSILON);
		}

		public void assertConnectionAimsAt(Point desiredLocation) {
			assertTrue(nameOfEdgeCreationTool + ": The connection doesn't aim at the desired location",
					isStraightLine(desiredLocation, srcAnchorPoint, targetAnchorPoint));
		}

		public void assertSrcAnchorPointOnLine(Point a, Point b) {
			assertTrue("Source of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified straight line",
					isStraightLine(a, srcAnchorPoint, b));
		}

		public void assertTargetAnchorPointOnLine(Point a, Point b) {
			assertTrue(
					"Target of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified straight line",
					isStraightLine(a, targetAnchorPoint, b));
		}

		public void assertTargetAnchorPointOnCircleAround(Point desiredLocation, double radius) {
			//System.out.println("radius - distance: " + (radius + PIXEL_OFFSET) + " - " + targetAnchorPoint.getDistance(desiredLocation));
			assertTrue("Target of " + nameOfEdgeCreationTool + ": The AnchorPoint is not on the specified circle line",
					Math.abs(radius - targetAnchorPoint.getDistance(desiredLocation)) <= EPSILON);
		}

		public void deleteEdge() {
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
			//System.out.println(unitVectorTowardsTarget1 + ", " + unitVectorTowardsTarget2 + ", distance = " + distanceOfUnitVectors);
			return distanceOfUnitVectors < EPSILON;
		}
	}

	/**
	 * Assembly Connector
	 */
	@Test
	public void testAssemblyConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("operationRequiredRoleNode",
				"operationProvidedRoleNode", "AssemblyConnector");

		// Calculate desired Values and compare Values

		// 1st: OperationRequiredRoleNode --> Top Center and 0.25 * length down
		Point srcDesiredPoint = cInit.getSrcNodeRect().getTop().translate(0, 7.5);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredPoint);
		cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

		// 2nd: OperationProvidedRoleNode --> Bottom Center and 10 pixel up
		Point targetDesiredPoint = cInit.getTargetNodeRect().getBottom().translate(0, -10);
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredPoint);
		double radius = 7.0;
		cInit.assertTargetAnchorPointOnCircleAround(targetDesiredPoint, radius + PIXEL_OFFSET);

		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}
	
	/**
	 * Assembly Infrastructure Connector
	 */
	@Test
	public void testAssemblyInfrastructureConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	/**
	 * Assembly Event Connector
	 */
	@Test
	public void testAssemblyEventConnectorPointsAfterEdgeCreation() {
		//TODO
	}

	/**
	 * Event Channel Source Connector
	 */
	@Test
	public void testEventChannelSourceConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "<<EventChannel>>\n" + "eventChannel",
				"EventChannelSourceConnector");

		// Calculate desired Values and compare Values

		// 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
		Point srcDesiredPoint = cInit.getSrcNodeRect().getBottom().translate(0, -12);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredPoint);
		cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

		// 2nd: Target should be on Event Channel border, aiming towards the center
		Point targetDesiredCenterPoint = cInit.getTargetNodeRect().getCenter();
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredCenterPoint);
		cInit.assertConnectionAimsAt(targetDesiredCenterPoint);

		// 3rd: If target is on the border, it is between corner points of the diamond
		// shape
		Point topOfDiamondShape = cInit.getTargetNodeRect().getTop();
		Point leftOfDiamondShape = cInit.getTargetNodeRect().getLeft();
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(topOfDiamondShape);
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(leftOfDiamondShape);
		cInit.assertTargetAnchorPointOnLine(leftOfDiamondShape, topOfDiamondShape);

		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}

	/**
	 * Event Channel Sink Connector
	 */
	@Test
	public void testEventChannelSinkConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("<<EventChannel>>\n" + "eventChannel", "sinkRoleNode",
				"EventChannelSinkConnector");

		// Calculate desired Values and compare Values

		// 1st: Source should be on Event Channel border, aiming towards the center
		Point srcDesiredCenterPoint = cInit.getSrcNodeRect().getCenter();
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(srcDesiredCenterPoint);
		cInit.assertConnectionAimsAt(srcDesiredCenterPoint);

		// 2nd: If source is on the border, it is between corner points of the diamond shape
		Point leftOfDiamondShape = cInit.getSrcNodeRect().getLeft();
		Point bottomOfDiamondShape = cInit.getSrcNodeRect().getBottom();
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(leftOfDiamondShape);
		getEditPart(cInit.getSrcNode()).getFigure().translateToAbsolute(bottomOfDiamondShape);
		cInit.assertSrcAnchorPointOnLine(leftOfDiamondShape, bottomOfDiamondShape);

		// 3rd: SinkRoleNode --> Top Center and 0.15 * length down
		Point targetDesiredPoint = cInit.getTargetNodeRect().getTop().translate(0, 4.5);
		getEditPart(cInit.getTargetNode()).getFigure().translateToAbsolute(targetDesiredPoint);
		cInit.assertTargetAnchorPointEqualsWithOffset(targetDesiredPoint, PIXEL_OFFSET);

		// Edge deletion, cleaning for next test
		cInit.deleteEdge();
	}
	
	/**
	 * Source Delegation Connector
	 */
	@Test
	public void testSourceDelegationConnectorPointsAfterEdgeCreation() {

		// Init Connection
		ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "externalSourceRoleNode",
				"SourceDelegationConnector");

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
	
	/**
	 * Sink Delegation Connector
	 */
	@Test
	public void testSinkDelegationConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	/**
	 * Provided Delegation Connector
	 */
	@Test
	public void testProvidedDelegationConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	/**
	 * Required Delegation Connector
	 */
	@Test
	public void testRequiredDelegationConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	/**
	 * Provided Infrastructure Delegation Connector
	 */
	@Test
	public void testProvidedInfrastructureDelegationConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	/**
	 * Required Infrastructure Delegation Connector
	 */
	@Test
	public void testRequiredInfrastructureDelegationConnectorPointsAfterEdgeCreation() {
		//TODO
	}
	
	
}