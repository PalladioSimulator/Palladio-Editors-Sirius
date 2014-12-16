package org.scaledl.architecturaltemplates.completion.config;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * This class defines a tab with AT-specific configuration options.
 * 
 * @author Sebastian Lehrig
 */
public class ATExtensionTab extends AbstractLaunchConfigurationTab {

    /**
     * Default configuration for simulation of failure-on-demand occurrences.
     */
    private static final Boolean DEFAULT_SIMULATE_FAILURES = false;

    /**
     * Name of configuration attribute for failure-on-demand simulation.
     */
    public static final String SIMULATE_FAILURES = "reliability.simulateFailures";

    /**
     * Button for control of failure-on-demand simulation.
     */
    private Button simulateFailuresButton;

    @Override
    public final void createControl(final Composite parent) {

        final SelectionListener selectionListener = new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                ATExtensionTab.this.setDirty(true);
                ATExtensionTab.this.updateLaunchConfigurationDialog();
            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                ATExtensionTab.this.setDirty(true);
                ATExtensionTab.this.updateLaunchConfigurationDialog();
            }
        };
        // Create the top-level container:
        Composite container = new Composite(parent, SWT.NONE);

        setControl(container);
        container.setLayout(new GridLayout());

        // Create reliability section:
        final Group reliabilityGroup = new Group(container, SWT.NONE);
        reliabilityGroup.setText("Architectural Templates");
        final GridData gdReliabilityGroup = new GridData(SWT.FILL, SWT.CENTER, true, false);
        reliabilityGroup.setLayoutData(gdReliabilityGroup);
        reliabilityGroup.setLayout(new GridLayout());
        simulateFailuresButton = new Button(reliabilityGroup, SWT.CHECK);
        final GridData gdSimulateFailuresButton = new GridData(SWT.FILL, SWT.CENTER, true, false);
        simulateFailuresButton.setLayoutData(gdSimulateFailuresButton);
        simulateFailuresButton.setText("Simulate failures");
        simulateFailuresButton.addSelectionListener(selectionListener);

    }

    @Override
    public final String getName() {
        return "Architectural Templates";
    }

    @Override
    public final void initializeFrom(final ILaunchConfiguration configuration) {
        try {
            simulateFailuresButton.setSelection(configuration.getAttribute(SIMULATE_FAILURES, true));
        } catch (CoreException e) {
            simulateFailuresButton.setSelection(false);
        }
    }

    @Override
    public final void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(SIMULATE_FAILURES, this.simulateFailuresButton.getSelection());
    }

    @Override
    public final void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
        configuration.setAttribute(SIMULATE_FAILURES, DEFAULT_SIMULATE_FAILURES);
    }

}
