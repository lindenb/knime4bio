package fr.inserm.umr915.knime4ngs.nodes.unix.echo;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class EchoNodeFactory
        extends NodeFactory<EchoNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public EchoNodeModel createNodeModel() {
        return new EchoNodeModel();
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
    public NodeView<EchoNodeModel> createNodeView(final int viewIndex,
            final EchoNodeModel nodeModel) {
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
        return new EchoNodeDialog();
    }

}

