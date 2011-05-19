package fr.inserm.umr915.knime4ngs.nodes.vcf.homopolymer;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class HomoPolymerNodeFactory 
        extends NodeFactory<HomoPolymerNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public HomoPolymerNodeModel createNodeModel() {
        return new HomoPolymerNodeModel();
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
    public NodeView<HomoPolymerNodeModel> createNodeView(final int viewIndex,
            final HomoPolymerNodeModel nodeModel) {
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
        return new HomoPolymerNodeDialog();
    }

}

