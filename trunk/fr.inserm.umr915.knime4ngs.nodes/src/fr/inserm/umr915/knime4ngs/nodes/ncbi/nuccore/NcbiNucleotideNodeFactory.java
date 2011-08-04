package fr.inserm.umr915.knime4ngs.nodes.ncbi.nuccore;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class NcbiNucleotideNodeFactory
        extends NodeFactory<AbstractNcbiSeqNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractNcbiSeqNodeModel createNodeModel() {
        return new AbstractNcbiSeqNodeModel()
        	{
        	@Override
        	protected String getDatabase() {
        		return "nucleotide";
        		}
        	};
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
    public NodeView<AbstractNcbiSeqNodeModel> createNodeView(final int viewIndex,
            final AbstractNcbiSeqNodeModel nodeModel) {
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
        return new NcbiSequenceNodeDialog();
    }

}

