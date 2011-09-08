package fr.inserm.umr915.knime4ngs.nodes.vcf.readsimple;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "VCFLoader" Node.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class ReadOneVCFNodeFactory 
        extends NodeFactory<ReadOneVCFNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadOneVCFNodeModel createNodeModel() {
        return new ReadOneVCFNodeModel();
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
    public NodeView<ReadOneVCFNodeModel> createNodeView(final int viewIndex,
            final ReadOneVCFNodeModel nodeModel) {
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
        return new ReadOneVCFNodeDialog();
    }

}

