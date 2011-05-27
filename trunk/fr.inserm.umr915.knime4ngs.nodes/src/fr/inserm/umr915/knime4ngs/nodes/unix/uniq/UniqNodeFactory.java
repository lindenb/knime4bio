package fr.inserm.umr915.knime4ngs.nodes.unix.uniq;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class UniqNodeFactory
        extends NodeFactory<UniqNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqNodeModel createNodeModel() {
        return new UniqNodeModel();
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
    public NodeView<UniqNodeModel> createNodeView(final int viewIndex,
            final UniqNodeModel nodeModel) {
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
        return new UniqNodeDialog();
    }

}

