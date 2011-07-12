package fr.inserm.umr915.knime4ngs.nodes.vcf.gn;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


@Deprecated
public class GeneNameNodeFactory
        extends NodeFactory<GeneNameNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GeneNameNodeModel createNodeModel() {
        return new GeneNameNodeModel();
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
    public NodeView<GeneNameNodeModel> createNodeView(final int viewIndex,
            final GeneNameNodeModel nodeModel) {
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
        return new GeneNameNodeDialog();
    }

}

