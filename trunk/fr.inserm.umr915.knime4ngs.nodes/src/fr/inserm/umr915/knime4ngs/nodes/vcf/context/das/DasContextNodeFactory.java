package fr.inserm.umr915.knime4ngs.nodes.vcf.context.das;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class DasContextNodeFactory
        extends NodeFactory<DasContextNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public DasContextNodeModel createNodeModel() {
        return new DasContextNodeModel();
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
    public NodeView<DasContextNodeModel> createNodeView(final int viewIndex,
            final DasContextNodeModel nodeModel) {
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
        return new DasContextNodeDialog();
    }

}

