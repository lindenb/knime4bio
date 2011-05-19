package fr.inserm.umr915.knime4ngs.nodes.bed.writer;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class BedWriterNodeFactory
        extends NodeFactory<BedWriterNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BedWriterNodeModel createNodeModel() {
        return new BedWriterNodeModel();
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
    public NodeView<BedWriterNodeModel> createNodeView(final int viewIndex,
            final BedWriterNodeModel nodeModel) {
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
        return new BedWriterNodeDialog();
    }

}

