package fr.inserm.umr915.knime4ngs.nodes.vcf.extractfmt;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ExtractFormatNodeFactory
        extends NodeFactory<ExtractFormatNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtractFormatNodeModel createNodeModel() {
        return new ExtractFormatNodeModel();
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
    public NodeView<ExtractFormatNodeModel> createNodeView(final int viewIndex,
            final ExtractFormatNodeModel nodeModel) {
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
        return new ExtractFormatNodeDialog();
    }

}

