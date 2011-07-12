package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.basic;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


@Deprecated
public class BasicPredictionNodeFactory 
        extends NodeFactory<BasicPredictionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BasicPredictionNodeModel createNodeModel() {
        return new BasicPredictionNodeModel();
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
    public NodeView<BasicPredictionNodeModel> createNodeView(final int viewIndex,
            final BasicPredictionNodeModel nodeModel) {
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
        return new BasicPredictionNodeDialog();
    }

}

