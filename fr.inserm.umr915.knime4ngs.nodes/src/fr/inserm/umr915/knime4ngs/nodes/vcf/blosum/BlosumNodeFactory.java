package fr.inserm.umr915.knime4ngs.nodes.vcf.blosum;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class BlosumNodeFactory
        extends NodeFactory<BlosumNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BlosumNodeModel createNodeModel() {
        return new BlosumNodeModel();
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
    public NodeView<BlosumNodeModel> createNodeView(final int viewIndex,
            final BlosumNodeModel nodeModel)
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
        return new BlosumNodeDialog();
    }

}

