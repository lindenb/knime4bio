package fr.inserm.umr915.knime4ngs.nodes.vcf.extractfmtmulti;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ExtractFormatMultiNodeFactory
        extends NodeFactory<ExtractFormatMultiNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtractFormatMultiNodeModel createNodeModel() {
        return new ExtractFormatMultiNodeModel();
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
    public NodeView<ExtractFormatMultiNodeModel> createNodeView(final int viewIndex,
            final ExtractFormatMultiNodeModel nodeModel) {
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
        return new ExtractFormatMultiNodeDialog();
    }

}

