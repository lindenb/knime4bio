package fr.inserm.umr915.knime4ngs.nodes.vcf.aggregation;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class AggregationNodeFactory
        extends NodeFactory<AggregationNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public AggregationNodeModel createNodeModel() {
        return new AggregationNodeModel();
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
    public NodeView<AggregationNodeModel> createNodeView(final int viewIndex,
            final AggregationNodeModel nodeModel)
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
        return new AggregationNodeDialog();
    }

}

