package fr.inserm.umr915.knime4ngs.nodes.vcf.bed;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class InRegionNodeFactory
        extends NodeFactory<InRegionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public InRegionNodeModel createNodeModel() {
        return new InRegionNodeModel();
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
    public NodeView<InRegionNodeModel> createNodeView(final int viewIndex,
            final InRegionNodeModel nodeModel) {
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
        return new InRegionNodeDialog();
    }

}

