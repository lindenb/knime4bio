package fr.inserm.umr915.knime4ngs.nodes.vcf.rs;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

@Deprecated
public class RsFilterNodeFactory 
        extends NodeFactory<RsFilterNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RsFilterNodeModel createNodeModel() {
        return new RsFilterNodeModel();
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
    public NodeView<RsFilterNodeModel> createNodeView(final int viewIndex,
            final RsFilterNodeModel nodeModel) {
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
        return new RsFilterNodeDialog();
    }

}

