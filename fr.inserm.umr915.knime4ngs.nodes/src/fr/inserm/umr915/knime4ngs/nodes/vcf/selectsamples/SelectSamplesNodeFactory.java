package fr.inserm.umr915.knime4ngs.nodes.vcf.selectsamples;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class SelectSamplesNodeFactory
        extends NodeFactory<SelectSamplesNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SelectSamplesNodeModel createNodeModel() {
        return new SelectSamplesNodeModel();
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
    public NodeView<SelectSamplesNodeModel> createNodeView(final int viewIndex,
            final SelectSamplesNodeModel nodeModel)
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
        return new SelectSamplesNodeDialog();
    }

}

