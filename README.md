Palladio-Core-Editors
=====================
Editors for models of the Palladio Component Model (PCM).

General Information
-----------------------------------------
https://sdqweb.ipd.kit.edu/wiki/PCM_Development/Sirius_Editors

Eclipse Update Sites
-----------------------------------------

##### Latest Build
- https://sdqweb.ipd.kit.edu/eclipse/palladio/core/editors/releases/latest/

##### Nightly Build
- https://sdqweb.ipd.kit.edu/eclipse/palladio/core/editors/nightly/

Develop Sirius Editors
-----------------------------------------

1. Download and install Eclipse Modeling Tools
2. Download and install Eclipse plugin dependencies for Palladio development.
	- Go to Eclipse->Help->Install New Software
	- Add Architectural Templates nightly update site: "https://sdqweb.ipd.kit.edu/eclipse/palladiosimulator/releases/latest/".
	- Install Palladio features.
3. Download and install Eclipse plugin dependencies for Architectural Templates development.
	- Go to Eclipse->Help->Install New Software
	- Add Architectural Templates nightly update site: "http://cloudscale.xlab.si/cse/updatesites/architecturaltemplates/nightly/".
	- Install Architectural Templates feature.
4. Clone repository
	$ git clone https://github.com/PalladioSimulator/Palladio-Core-Editors.git
5. Import Palladio-Core-Editors plugins, under "plugins/" directory, into the workbench.


Features and Plugins
--------------------
##### Features
- org.palladiosimulator.editors.feature

##### Included Plugins
- org.palladiosimulator.editors.composedprovidingrequiringentity.design
- org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension
- org.palladiosimulator.editors.util
