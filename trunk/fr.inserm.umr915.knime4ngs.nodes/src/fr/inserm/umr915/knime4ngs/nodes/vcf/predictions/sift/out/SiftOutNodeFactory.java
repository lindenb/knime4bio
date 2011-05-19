package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.out;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SiftOutNodeFactory 
        extends NodeFactory<SiftOutNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SiftOutNodeModel createNodeModel() {
        return new SiftOutNodeModel();
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
    public NodeView<SiftOutNodeModel> createNodeView(final int viewIndex,
            final SiftOutNodeModel nodeModel) {
        throw new IllegalStateException("No view Defined");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new SiftOutNodeDialog();
    }

}

