package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.ensembl;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

public class EnsemblGCNodeFactory 
        extends NodeFactory<EnsemblGCNodeModel>
	{
    /**
     * {@inheritDoc}
     */
    @Override
    public EnsemblGCNodeModel createNodeModel() {
        return new EnsemblGCNodeModel();
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
    public NodeView<EnsemblGCNodeModel> createNodeView(final int viewIndex,
            final EnsemblGCNodeModel nodeModel) {
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
        return new EnsemblGCNodeDialog();
    }

}

