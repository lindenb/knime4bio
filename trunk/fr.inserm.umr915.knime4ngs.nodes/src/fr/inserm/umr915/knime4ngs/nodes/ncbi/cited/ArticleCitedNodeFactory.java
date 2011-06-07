package fr.inserm.umr915.knime4ngs.nodes.ncbi.cited;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ArticleCitedNodeFactory
        extends NodeFactory<ArticleCitedNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleCitedNodeModel createNodeModel() {
        return new ArticleCitedNodeModel();
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
    public NodeView<ArticleCitedNodeModel> createNodeView(final int viewIndex,
            final ArticleCitedNodeModel nodeModel) {
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
        return new ArticleCitedNodeDialog();
    }

}

