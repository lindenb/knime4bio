package fr.inserm.umr915.knime4ngs.nodes.vcf.context.sam;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SamContextNodeFactory
        extends NodeFactory<SamContextNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SamContextNodeModel createNodeModel() {
        return new SamContextNodeModel();
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
    public NodeView<SamContextNodeModel> createNodeView(final int viewIndex,
            final SamContextNodeModel nodeModel) {
        throw new IllegalStateException();
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
        return new SamContextNodeDialog();
    }

}

