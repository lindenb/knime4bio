package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.generic;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class GenericUCSCNodeFactory
        extends NodeFactory<GenericUCSCNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericUCSCNodeModel createNodeModel() {
        return new GenericUCSCNodeModel();
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
    public NodeView<GenericUCSCNodeModel> createNodeView(final int viewIndex,
            final GenericUCSCNodeModel nodeModel) {
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
        return new GenericUCSCNodeDialog();
    }

}

