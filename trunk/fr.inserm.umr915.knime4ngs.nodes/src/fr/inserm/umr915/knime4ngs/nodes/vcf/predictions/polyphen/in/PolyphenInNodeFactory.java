package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.polyphen.in;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class PolyphenInNodeFactory 
        extends NodeFactory<PolyphenInNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public PolyphenInNodeModel createNodeModel() {
        return new PolyphenInNodeModel();
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
    public NodeView<PolyphenInNodeModel> createNodeView(final int viewIndex,
            final PolyphenInNodeModel nodeModel)
        {
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
        return new PolyphenInNodeDialog();
    }

}

