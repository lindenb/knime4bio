package fr.inserm.umr915.knime4ngs.nodes.vcf.extractsnpeff;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class ExtractSnpEffNodeFactory
        extends NodeFactory<ExtractSnpEffNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ExtractSnpEffNodeModel createNodeModel() {
        return new ExtractSnpEffNodeModel();
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
    public NodeView<ExtractSnpEffNodeModel> createNodeView(final int viewIndex,
            final ExtractSnpEffNodeModel nodeModel) {
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
        return new ExtractSnpEffNodeDialog();
    }

}

