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

/**
 * A test class which compares the desired Locations for AnchorPoints with the actual returned
 * AnchorPoints and with the returned EndPoints of the Connector Edges. <br>
 * <br>
 * The testmodel and diagram being worked with is located in the folder
 * /testmodels/anchorProviderTest and should not be modified. <br>
 * <br>
 * The test has to be run as a JUnit Plug-in Test.
 *
 * @author Jonas Lehmann
 */
public class AnchorProviderTest extends SiriusDiagramTestCase {

    // Path to the test model folder
    private static final String TEST_MODEL_PATH = "/org.palladiosimulator.editors.sirius.tests/testmodels/anchorProviderTest/";
    // Append name of the .system model
    private static final String SEMANTIC_MODEL_PATH = TEST_MODEL_PATH + "MySystem.system";
    // Append name of the .aird file
    private static final String REPRESENTATION_MODEL_PATH = TEST_MODEL_PATH + "representations.aird";
    // Path to the .odesign of the model which is under observation.
    private static final String MODELER_PATH = "/org.palladiosimulator.editors.sirius.assembly/description/assembly.odesign";
    // Representation description name, like it is called in the aird file
    private static final String REPRESENTATION_DESC_NAME = "Assembly Diagram";
    // Viewpoint name of the model
    private static final String VIEWPOINT_NAME = "Assembly";

    // Constants for testing
    private static final double EPSILON = 0.01;
    private final static int PIXEL_OFFSET = 2;

    // Elements of the runtime
    private DDiagram diagram;
    private IEditorPart editorPart;
    private LocationRequest cursor;

    /**
     * Loads the model and diagram, sets up the editor to work with.
     */
    @Override
    protected void setUp() throws Exception {
        // Get the Assembly diagram
        super.setUp();
        this.genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        this.initViewpoint(VIEWPOINT_NAME);
        this.diagram = (DDiagram) this.getRepresentations(REPRESENTATION_DESC_NAME)
            .toArray()[0];

        assertNotNull(this.diagram);

        // Set up Default Cursor
        this.cursor = new LocationRequest();
        this.cursor.setLocation(new Point(690, 237));

        // run testing editor
        this.editorPart = DialectUIManager.INSTANCE.openEditor(this.session, this.diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Closes the editor and tears down all used resources at the end.
     */
    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(this.editorPart, false);
        TestsUtil.synchronizationWithUIThread();
        this.diagram = null;
        this.editorPart = null;
        super.tearDown();
    }

    /**
     * Data Handler for Points and Diagram Elements of a Connection. <br>
     * <br>
     * 1) Use the constructor to create a connection edge. <br>
     * 2) Use the assertion methods for checking values of the Points. <br>
     * <br>
     * 3) Use deleteEdge() for cleaning up the testmodel diagram for the next test.
     *
     * @author Jonas Lehmann
     */
    private class ConnectionInitializer {

        private Point srcAnchorPoint, srcEdgeEndPoint, targetAnchorPoint, targetEdgeEndPoint;

        private final String nameOfEdgeCreationTool;

        private DDiagramElement srcNode, targetNode;
        private NodeEditPart srcNodeEP, targetNodeEP;
        private Rectangle srcNodeRect, targetNodeRect;

        private DEdge dEdge;
        private Connection connection;

        /**
         * Creates a new {@link ConnectionInitializer}.
         *
         * @param srcName
         *            the name of the starting node in the assembly diagram
         * @param targetName
         *            the name of the target node in the assembly diagram
         * @param nameOfEdgeCreationTool
         *            the name of the connector needed to connect the two nodes
         */
        public ConnectionInitializer(final String srcName, final String targetName,
                final String nameOfEdgeCreationTool) {
            this(srcName, targetName, nameOfEdgeCreationTool, true);
        }

        /**
         * Alternative Constructor to run/skip comparison with edge end points.
         *
         * @param srcName
         *            the name of the starting node in the assembly diagram
         * @param targetName
         *            the name of the target node in the assembly diagram
         * @param nameOfEdgeCreationTool
         *            the name of the connector needed to connect the two nodes
         * @param compareWithEdgeEndPoints
         *            if comparison is needed set true
         */
        public ConnectionInitializer(final String srcName, final String targetName, final String nameOfEdgeCreationTool,
                final boolean compareWithEdgeEndPoints) {
            // template method for all things to process after one another
            this.nameOfEdgeCreationTool = nameOfEdgeCreationTool;
            this.initElements(srcName, targetName);
            this.initAnchorPoints();
            this.createEdge();
            this.initEdgeEndPoints();
            if (compareWithEdgeEndPoints) {
                this.compareAnchorWithEdgeEndPoints();
            }
        }

        private void initElements(final String srcName, final String targetName) {
            // Source Node
            this.srcNode = AnchorProviderTest.this.getDiagramElementsFromLabel(AnchorProviderTest.this.diagram, srcName)
                .get(0);
            this.srcNodeEP = (NodeEditPart) AnchorProviderTest.this.getEditPart(this.srcNode);
            this.srcNodeRect = AnchorProviderTest.this.getEditPart(this.srcNode)
                .getFigure()
                .getBounds();

            // Target Node
            this.targetNode = AnchorProviderTest.this
                .getDiagramElementsFromLabel(AnchorProviderTest.this.diagram, targetName)
                .get(0);
            this.targetNodeEP = (NodeEditPart) AnchorProviderTest.this.getEditPart(this.targetNode);
            this.targetNodeRect = AnchorProviderTest.this.getEditPart(this.targetNode)
                .getFigure()
                .getBounds();
        }

        private void initAnchorPoints() {
            final Point referencePointForSource = this.srcNodeEP
                .getSourceConnectionAnchor(AnchorProviderTest.this.cursor)
                .getLocation(new PrecisionPoint(0, 0));
            final Point referencePointForTarget = this.targetNodeEP
                .getTargetConnectionAnchor(AnchorProviderTest.this.cursor)
                .getLocation(new PrecisionPoint(0, 0));
            this.targetAnchorPoint = this.targetNodeEP.getTargetConnectionAnchor(AnchorProviderTest.this.cursor)
                .getLocation(referencePointForSource);
            this.srcAnchorPoint = this.srcNodeEP.getSourceConnectionAnchor(AnchorProviderTest.this.cursor)
                .getLocation(referencePointForTarget);
        }

        private void createEdge() {
            assertEquals(0, AnchorProviderTest.this.diagram.getEdges()
                .size());
            AnchorProviderTest.this.applyEdgeCreationTool(this.nameOfEdgeCreationTool, AnchorProviderTest.this.diagram,
                    (EdgeTarget) this.srcNode, (EdgeTarget) this.targetNode);
            this.dEdge = AnchorProviderTest.this.diagram.getEdges()
                .get(0);
            assertNotNull(this.dEdge);
            this.connection = ((ConnectionEditPart) AnchorProviderTest.this.getEditPart(this.dEdge))
                .getConnectionFigure();
        }

        private void initEdgeEndPoints() {
            this.srcEdgeEndPoint = this.connection.getPoints()
                .getFirstPoint();
            this.targetEdgeEndPoint = this.connection.getPoints()
                .getLastPoint();
        }

        private void compareAnchorWithEdgeEndPoints() {
            assertEquals(
                    "Source of " + this.nameOfEdgeCreationTool
                            + ": The Edges Endpoint differs from the Anchor Point location",
                    this.srcAnchorPoint, this.srcEdgeEndPoint);
            assertEquals(
                    "Target of " + this.nameOfEdgeCreationTool
                            + ": The Edges Endpoint differs from the Anchor Point location",
                    this.targetAnchorPoint, this.targetEdgeEndPoint);
        }

        public void assertSrcAnchorPointEquals(final Point desiredLocation) {
            assertEquals(
                    "Source of " + this.nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
                    this.srcAnchorPoint, desiredLocation);
        }

        public void assertTargetAnchorPointEquals(final Point desiredLocation) {
            assertEquals(
                    "Target of " + this.nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
                    this.targetAnchorPoint, desiredLocation);
        }

        public void assertTargetAnchorPointEqualsWithOffset(final Point desiredLocation, final double offset) {
            assertTrue(
                    "Target of " + this.nameOfEdgeCreationTool + ": The AnchorPoint differs from its desired location",
                    Math.abs(offset - this.targetAnchorPoint.getDistance(desiredLocation)) <= EPSILON);
        }

        public void assertConnectionAimsAt(final Point desiredLocation) {
            assertTrue(this.nameOfEdgeCreationTool + ": The connection doesn't aim at the desired location",
                    this.isStraightLine(desiredLocation, this.srcAnchorPoint, this.targetAnchorPoint));
        }

        public void assertSrcAnchorPointOnLine(final Point a, final Point b) {
            assertTrue(
                    "Source of " + this.nameOfEdgeCreationTool
                            + ": The AnchorPoint is not on the specified straight line",
                    this.isStraightLine(a, this.srcAnchorPoint, b));
        }

        public void assertTargetAnchorPointOnLine(final Point a, final Point b) {
            assertTrue(
                    "Target of " + this.nameOfEdgeCreationTool
                            + ": The AnchorPoint is not on the specified straight line",
                    this.isStraightLine(a, this.targetAnchorPoint, b));
        }

        public void assertTargetAnchorPointOnCircleAround(final Point desiredLocation, final double radius) {
            assertTrue(
                    "Target of " + this.nameOfEdgeCreationTool
                            + ": The AnchorPoint is not on the specified circle line",
                    Math.abs(radius - Math.round(this.targetAnchorPoint.getDistance(desiredLocation))) <= EPSILON);
        }

        private boolean isStraightLine(final Point a, final Point middle, final Point b) {
            final PrecisionPoint src = new PrecisionPoint(a);
            final PrecisionPoint target1 = new PrecisionPoint(middle);
            final PrecisionPoint target2 = new PrecisionPoint(b);

            final double distanceSrcTarget1 = src.getDistance(target1);
            final Point unitVectorTowardsTarget1 = target1.getTranslated(src.getNegated())
                .getScaled(1 / distanceSrcTarget1);
            final double distanceSrcTarget2 = src.getDistance(target2);
            final Point unitVectorTowardsTarget2 = target2.getTranslated(src.getNegated())
                .getScaled(1 / distanceSrcTarget2);

            final double distanceOfUnitVectors = unitVectorTowardsTarget1.getDistance(unitVectorTowardsTarget2);
            return distanceOfUnitVectors < EPSILON;
        }

        public void deleteEdge() {
            AnchorProviderTest.this.applyDeletionTool(this.dEdge);
            assertEquals(0, AnchorProviderTest.this.diagram.getEdges()
                .size());
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
    }

    /**
     * Assembly Connector
     */
    @Test
    public void testAssemblyConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("operationRequiredRoleNode",
                "operationProvidedRoleNode", "AssemblyConnector");

        // Calculate desired Values and compare Values

        // 1st: OperationRequiredRoleNode --> Top Center and 0.25 * length down
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getTop()
            .translate(0, 7.5);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: OperationProvidedRoleNode --> Bottom Center and 10 pixel up
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getBottom()
            .translate(0, -10);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        final double radius = 7.0;
        cInit.assertTargetAnchorPointOnCircleAround(targetDesiredPoint, radius + PIXEL_OFFSET);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Assembly Infrastructure Connector
     */
    @Test
    public void testAssemblyInfrastructureConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("infrastructureRequiredRoleNode",
                "infrastructureProvidedRoleNode", "AssemblyInfrastructureConnector");

        // Calculate desired Values and compare Values

        // 1st: infrastructureRequiredRoleNode --> Right Center and 1/3 * length left
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getRight()
            .translate(-10, 0);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: infrastructureProvidedRoleNode --> Bottom Center, 11 right and 8 up
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getBottom()
            .translate(11, -8);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEquals(targetDesiredPoint);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Assembly Event Connector
     */
    @Test
    public void testAssemblyEventConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "sinkRoleNode",
                "AssemblyEventConnector");

        // Calculate desired Values and compare Values

        // 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getBottom()
            .translate(0, -12);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: SinkRoleNode --> Top Center and 0.15 * length down
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getTop()
            .translate(0, 4.5);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEqualsWithOffset(targetDesiredPoint, PIXEL_OFFSET);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Event Channel Source Connector
     */
    @Test
    public void testEventChannelSourceConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode",
                "<<EventChannel>>\n" + "eventChannel", "EventChannelSourceConnector");

        // Calculate desired Values and compare Values

        // 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getBottom()
            .translate(0, -12);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: Target should be on Event Channel border, aiming towards the center
        final Point targetDesiredCenterPoint = cInit.getTargetNodeRect()
            .getCenter();
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredCenterPoint);
        cInit.assertConnectionAimsAt(targetDesiredCenterPoint);

        // 3rd: If target is on the border, it is between corner points of the diamond
        // shape
        final Point topOfDiamondShape = cInit.getTargetNodeRect()
            .getTop();
        final Point leftOfDiamondShape = cInit.getTargetNodeRect()
            .getLeft();
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(topOfDiamondShape);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(leftOfDiamondShape);
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
        final ConnectionInitializer cInit = new ConnectionInitializer("<<EventChannel>>\n" + "eventChannel",
                "sinkRoleNode", "EventChannelSinkConnector", false);

        // Calculate desired Values and compare Values

        // 1st: Source should be on Event Channel border, aiming towards the center
        final Point srcDesiredCenterPoint = cInit.getSrcNodeRect()
            .getCenter();
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredCenterPoint);
        cInit.assertConnectionAimsAt(srcDesiredCenterPoint);

        // 2nd: If source is on the border, it is between corner points of the diamond
        // shape
        final Point leftOfDiamondShape = cInit.getSrcNodeRect()
            .getLeft();
        final Point bottomOfDiamondShape = cInit.getSrcNodeRect()
            .getBottom();
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(leftOfDiamondShape);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(bottomOfDiamondShape);
        cInit.assertSrcAnchorPointOnLine(leftOfDiamondShape, bottomOfDiamondShape);

        // 3rd: SinkRoleNode --> Top Center and 0.15 * length down
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getTop()
            .translate(0, 4.5);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
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
        final ConnectionInitializer cInit = new ConnectionInitializer("sourceRoleNode", "externalSourceRoleNode",
                "SourceDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: SourceRoleNode --> Bottom Center and 0.4 * length up
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getBottom()
            .translate(0, -12);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: ExternalSourceRoleNode --> Left Center (because external role)
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getLeft();
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEquals(targetDesiredPoint);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Sink Delegation Connector
     */
    @Test
    public void testSinkDelegationConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("externalSinkRoleNode", "sinkRoleNode",
                "SinkDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: ExternalSourceRoleNode --> Left Center (because external role)
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getLeft();
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: SinkRoleNode --> Top Center and 0.15 * length down
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getTop()
            .translate(0, 4.5);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEqualsWithOffset(targetDesiredPoint, PIXEL_OFFSET);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Provided Delegation Connector
     */
    @Test
    public void testProvidedDelegationConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("externalProvidedRoleNode",
                "operationProvidedRoleNode", "ProvidedDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: externalProvidedRoleNode --> Right Center (because external role)
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getRight();
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: OperationProvidedRoleNode --> Bottom Center and 10 pixel up
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getBottom()
            .translate(0, -10);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        final double radius = 7.0;
        cInit.assertTargetAnchorPointOnCircleAround(targetDesiredPoint, radius + PIXEL_OFFSET);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Required Delegation Connector
     */
    @Test
    public void testRequiredDelegationConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("operationRequiredRoleNode",
                "externalRequiredRoleNode", "RequiredDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: OperationRequiredRoleNode --> Top Center and 0.25 * length down
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getTop()
            .translate(0, 7.5);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: externalRequiredRoleNode --> Right Center (because external role)
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getRight();
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEquals(targetDesiredPoint);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Provided Infrastructure Delegation Connector
     */
    @Test
    public void testProvidedInfrastructureDelegationConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("externalInfrastructureProvidedRoleNode",
                "infrastructureProvidedRoleNode", "ProvidedInfrastructureDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: infrastructureRequiredRoleNode --> Bottom Center (because external role)
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getBottom();
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: infrastructureProvidedRoleNode --> Top Center, 12 right and 8 down
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getTop()
            .translate(12, 8);
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEquals(targetDesiredPoint);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }

    /**
     * Required Infrastructure Delegation Connector
     */
    @Test
    public void testRequiredInfrastructureDelegationConnectorPointsAfterEdgeCreation() {

        // Init Connection
        final ConnectionInitializer cInit = new ConnectionInitializer("infrastructureRequiredRoleNode",
                "externalInfrastructureRequiredRoleNode", "RequiredInfrastructureDelegationConnector");

        // Calculate desired Values and compare Values

        // 1st: infrastructureRequiredRoleNode --> Right Center and 1/3 * length left
        final Point srcDesiredPoint = cInit.getSrcNodeRect()
            .getRight()
            .translate(-10, 0);
        this.getEditPart(cInit.getSrcNode())
            .getFigure()
            .translateToAbsolute(srcDesiredPoint);
        cInit.assertSrcAnchorPointEquals(srcDesiredPoint);

        // 2nd: externalInfrastructureRequiredRoleNode --> Top Center (because external
        // role)
        final Point targetDesiredPoint = cInit.getTargetNodeRect()
            .getTop();
        this.getEditPart(cInit.getTargetNode())
            .getFigure()
            .translateToAbsolute(targetDesiredPoint);
        cInit.assertTargetAnchorPointEquals(targetDesiredPoint);

        // Edge deletion, cleaning for next test
        cInit.deleteEdge();
    }
}