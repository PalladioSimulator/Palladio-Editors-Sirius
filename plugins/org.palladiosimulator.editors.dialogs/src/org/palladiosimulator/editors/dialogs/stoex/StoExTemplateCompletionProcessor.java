/**
 * 
 */
package org.palladiosimulator.editors.dialogs.stoex;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateCompletionProcessor;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.swt.graphics.Image;

// TODO: Auto-generated Javadoc
/**
 * The Class StoExTemplateCompletionProcessor.
 *
 * @author Steffen Becker
 */
public class StoExTemplateCompletionProcessor extends TemplateCompletionProcessor {

    /** The Constant STO_EX_CONTEXT_TYPE. */
    public static final String STO_EX_CONTEXT_TYPE = "StoExContextType";

    /**
     * Instantiates a new sto ex template completion processor.
     */
    public StoExTemplateCompletionProcessor() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.templates.TemplateCompletionProcessor#getContextType(org.eclipse.jface
     * .text.ITextViewer, org.eclipse.jface.text.IRegion)
     */
    @Override
    protected TemplateContextType getContextType(ITextViewer viewer, IRegion region) {
        return new TemplateContextType(STO_EX_CONTEXT_TYPE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.templates.TemplateCompletionProcessor#getImage(org.eclipse.jface.text
     * .templates.Template)
     */
    @Override
    protected Image getImage(Template template) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.text.templates.TemplateCompletionProcessor#getTemplates(java.lang.String)
     */
    @Override
    protected Template[] getTemplates(String contextTypeId) {
        List<Template> templates = new ArrayList<Template>();

        // Template with units:
        // templates.add(new Template("DoublePDF",
        // "Creates a double random variable (BoxedPDF)", STO_EX_CONTEXT_TYPE,
        // "DoublePDF(unit=\"${unit}\")[(${x};${p})(${y};${q})(${z};${r})]", false));
        // templates.add(new Template("IntPMF",
        // "Creates an integer random variable", STO_EX_CONTEXT_TYPE,
        // "IntPMF(unit=\"${unit}\")[(${x};${p})(${y};${q})(${z};${r})]", false));
        // templates.add(new Template("DoublePMF",
        // "Creates a double random variable", STO_EX_CONTEXT_TYPE,
        // "DoublePMF(unit=\"${unit}\")[(${x};${p})(${y};${q})(${z};${r})]", false));
        // templates.add(new Template("BoolPMF",
        // "Creates a boolean random variable", STO_EX_CONTEXT_TYPE,
        // "BoolPMF(unit=\"bool\")[(true;${p})(false;${q})]", false));
        // templates.add(new Template("EnumPMF",
        // "Creates a enum random variable", STO_EX_CONTEXT_TYPE,
        // "EnumPMF(unit=\"${unit}\")[(${x};${p})]", false));

        templates.add(new Template("DoublePDF", "Creates a double random variable (BoxedPDF)", STO_EX_CONTEXT_TYPE,
                "DoublePDF[(${x};${p})(${y};${q})(${z};${r})]", false));
        templates.add(new Template("IntPMF", "Creates an integer random variable", STO_EX_CONTEXT_TYPE,
                "IntPMF[(${x};${p})(${y};${q})(${z};${r})]", false));
        templates.add(new Template("DoublePMF", "Creates a double random variable", STO_EX_CONTEXT_TYPE,
                "DoublePMF[(${x};${p})(${y};${q})(${z};${r})]", false));
        templates.add(new Template("BoolPMF", "Creates a boolean random variable", STO_EX_CONTEXT_TYPE,
                "BoolPMF[(true;${p})(false;${q})]", false));
        templates.add(new Template("EnumPMF", "Creates a enum random variable", STO_EX_CONTEXT_TYPE,
                "EnumPMF[(${x};${p})]", false));

        return templates.toArray(new Template[templates.size()]);
    }
}
