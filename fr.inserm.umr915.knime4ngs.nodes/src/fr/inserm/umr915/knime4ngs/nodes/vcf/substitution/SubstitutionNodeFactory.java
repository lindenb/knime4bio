package fr.inserm.umr915.knime4ngs.nodes.vcf.substitution;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SubstitutionNodeFactory 
        extends NodeFactory<SubstitutionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SubstitutionNodeModel createNodeModel() {
        return new SubstitutionNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<SubstitutionNodeModel> createNodeView(final int viewIndex,
            final SubstitutionNodeModel nodeModel) {
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
    	throw new IllegalStateException();
    }

}

