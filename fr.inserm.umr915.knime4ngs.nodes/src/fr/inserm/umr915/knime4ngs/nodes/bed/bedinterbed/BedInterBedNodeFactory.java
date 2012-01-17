package fr.inserm.umr915.knime4ngs.nodes.bed.bedinterbed;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class BedInterBedNodeFactory
        extends NodeFactory<BedInterBedNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BedInterBedNodeModel createNodeModel() {
        return new BedInterBedNodeModel();
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
    public NodeView<BedInterBedNodeModel> createNodeView(final int viewIndex,
            final BedInterBedNodeModel nodeModel) {
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
        return new BedInterBedNodeDialog();
    }

}

