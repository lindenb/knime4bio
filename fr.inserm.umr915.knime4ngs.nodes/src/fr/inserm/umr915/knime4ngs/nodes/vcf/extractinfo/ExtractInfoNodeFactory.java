package fr.inserm.umr915.knime4ngs.nodes.vcf.extractinfo;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ExtractInfoNodeFactory
        extends NodeFactory<ExtractInfoNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtractInfoNodeModel createNodeModel() {
        return new ExtractInfoNodeModel();
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
    public NodeView<ExtractInfoNodeModel> createNodeView(final int viewIndex,
            final ExtractInfoNodeModel nodeModel) {
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
        return new ExtractInfoNodeDialog();
    }

}

