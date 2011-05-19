package fr.inserm.umr915.knime4ngs.nodes.vcf.nsfp;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class NsfpNodeFactory
        extends NodeFactory<NsfpNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NsfpNodeModel createNodeModel() {
        return new NsfpNodeModel();
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
    public NodeView<NsfpNodeModel> createNodeView(final int viewIndex,
            final NsfpNodeModel nodeModel)
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
        return new NsfpNodeDialog();
    }

}

