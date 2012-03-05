package fr.inserm.umr915.knime4ngs.nodes.vcf.readmulti;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "VCFLoader" Node.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class ReadMultiVCFNodeFactory 
        extends NodeFactory<ReadMultiVCFNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadMultiVCFNodeModel createNodeModel() {
        return new ReadMultiVCFNodeModel();
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
    public NodeView<ReadMultiVCFNodeModel> createNodeView(final int viewIndex,
            final ReadMultiVCFNodeModel nodeModel) {
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
        return new ReadMultiVCFNodeDialog();
    }

}

