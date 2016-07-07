package org.palladiosimulator.editors.sirius.custom.style.commands;

import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

/**
 * A value command.
 */
public class MySetValueCommand extends SetValueCommand {

    /**
     * 
     * @param request
     *            a request
     */
    public MySetValueCommand(SetRequest request) {
        super(request);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean canExecute() {
        return true;
    }

}
