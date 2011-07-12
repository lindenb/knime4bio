package fr.inserm.umr915.knime4ngs.nodes.vcf.load;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

@Deprecated
public class VCFLoaderNodeFactory 
        extends NodeFactory<VCFLoaderNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public VCFLoaderNodeModel createNodeModel() {
        return new VCFLoaderNodeModel();
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
    public NodeView<VCFLoaderNodeModel> createNodeView(final int viewIndex,
            final VCFLoaderNodeModel nodeModel) {
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
        return new VCFLoaderNodeDialog();
    }

}

