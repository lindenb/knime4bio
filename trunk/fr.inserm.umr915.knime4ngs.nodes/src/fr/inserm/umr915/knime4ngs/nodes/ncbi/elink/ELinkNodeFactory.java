package fr.inserm.umr915.knime4ngs.nodes.ncbi.elink;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ELinkNodeFactory
        extends NodeFactory<ELinkNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ELinkNodeModel createNodeModel() {
        return new ELinkNodeModel();
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
    public NodeView<ELinkNodeModel> createNodeView(final int viewIndex,
            final ELinkNodeModel nodeModel) {
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
        return new ELinkNodeDialog();
    }

}

