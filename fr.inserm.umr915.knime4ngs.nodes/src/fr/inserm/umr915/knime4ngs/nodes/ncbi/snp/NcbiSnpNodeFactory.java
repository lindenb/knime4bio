package fr.inserm.umr915.knime4ngs.nodes.ncbi.snp;

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
public class NcbiSnpNodeFactory
        extends NodeFactory<NcbiSnpNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NcbiSnpNodeModel createNodeModel() {
        return new NcbiSnpNodeModel();
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
    public NodeView<NcbiSnpNodeModel> createNodeView(final int viewIndex,
            final NcbiSnpNodeModel nodeModel) {
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

