package fr.inserm.umr915.knime4ngs.nodes.vcf.distance;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class DistanceSnpNodeFactory
        extends NodeFactory<DistanceSnpNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public DistanceSnpNodeModel createNodeModel() {
        return new DistanceSnpNodeModel();
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
    public NodeView<DistanceSnpNodeModel> createNodeView(final int viewIndex,
            final DistanceSnpNodeModel nodeModel)
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
        return new DistanceSnpNodeDialog();
    }

}

