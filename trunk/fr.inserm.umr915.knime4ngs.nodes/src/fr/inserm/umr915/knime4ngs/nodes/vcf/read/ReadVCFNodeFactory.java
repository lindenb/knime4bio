package fr.inserm.umr915.knime4ngs.nodes.vcf.read;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "VCFLoader" Node.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class ReadVCFNodeFactory 
        extends NodeFactory<ReadVCFNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadVCFNodeModel createNodeModel() {
        return new ReadVCFNodeModel();
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
    public NodeView<ReadVCFNodeModel> createNodeView(final int viewIndex,
            final ReadVCFNodeModel nodeModel) {
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
        return new ReadVCFNodeDialog();
    }

}

