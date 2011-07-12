package fr.inserm.umr915.knime4ngs.nodes.vcf.rsid;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


@Deprecated
public class RsIdNodeFactory 
        extends NodeFactory<RsidNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RsidNodeModel createNodeModel() {
        return new RsidNodeModel();
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
    public NodeView<RsidNodeModel> createNodeView(final int viewIndex,
            final RsidNodeModel nodeModel) {
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
        return new RsIdNodeDialog();
    }

}

