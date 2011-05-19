package fr.inserm.umr915.knime4ngs.nodes.vcf.havingid;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class HavingIdNodeFactory
        extends NodeFactory<HavingIdNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public HavingIdNodeModel createNodeModel() {
        return new HavingIdNodeModel();
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
    public NodeView<HavingIdNodeModel> createNodeView(final int viewIndex,
            final HavingIdNodeModel nodeModel)
        {
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
        return new HavingIdNodeDialog();
    }

}

