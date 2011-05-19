package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.out;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class PolyphenOutNodeFactory 
        extends NodeFactory<PolyphenOutNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public PolyphenOutNodeModel createNodeModel() {
        return new PolyphenOutNodeModel();
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
    public NodeView<PolyphenOutNodeModel> createNodeView(final int viewIndex,
            final PolyphenOutNodeModel nodeModel) {
        throw new IllegalStateException("No view Defined");
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
        return new PolyphenOutNodeDialog();
    }

}

