package fr.inserm.umr915.knime4ngs.nodes.vcf.uniprot;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class UniprotNodeFactory 
        extends NodeFactory<UniprotNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public UniprotNodeModel createNodeModel() {
        return new UniprotNodeModel();
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
    public NodeView<UniprotNodeModel> createNodeView(final int viewIndex,
            final UniprotNodeModel nodeModel) {
        throw new IllegalStateException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
    	throw new IllegalStateException();
    }

}

