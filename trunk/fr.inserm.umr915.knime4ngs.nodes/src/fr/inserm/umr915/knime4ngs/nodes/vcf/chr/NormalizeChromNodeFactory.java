package fr.inserm.umr915.knime4ngs.nodes.vcf.chr;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class NormalizeChromNodeFactory
        extends NodeFactory<NormalizeChromNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NormalizeChromNodeModel createNodeModel() {
        return new NormalizeChromNodeModel();
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
    public NodeView<NormalizeChromNodeModel> createNodeView(final int viewIndex,
            final NormalizeChromNodeModel nodeModel)
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
        return new NormalizeChromNodeDialog();
    }

}

