package fr.inserm.umr915.knime4ngs.nodes.vcf.numericsplit;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class VCFNumericSplitNodeFactory
        extends NodeFactory<VCFNumericSplitNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public VCFNumericSplitNodeModel createNodeModel() {
        return new VCFNumericSplitNodeModel();
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
    public NodeView<VCFNumericSplitNodeModel> createNodeView(final int viewIndex,
            final VCFNumericSplitNodeModel nodeModel)
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
        return new VCFNumericSplitNodeDialog();
    }

}

