package fr.inserm.umr915.knime4ngs.nodes.ncbi.pubmed;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

import fr.inserm.umr915.knime4ngs.nodes.ncbi.DefaultNcbiEUtilsNodeDialog;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class NcbiPubmedNodeFactory
        extends NodeFactory<NcbiPubmedNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NcbiPubmedNodeModel createNodeModel() {
        return new NcbiPubmedNodeModel();
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
    public NodeView<NcbiPubmedNodeModel> createNodeView(final int viewIndex,
            final NcbiPubmedNodeModel nodeModel) {
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
        return new DefaultNcbiEUtilsNodeDialog();
    }

}

