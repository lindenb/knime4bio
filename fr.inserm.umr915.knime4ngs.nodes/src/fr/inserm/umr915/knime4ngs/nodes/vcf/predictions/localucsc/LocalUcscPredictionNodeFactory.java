package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.localucsc;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class LocalUcscPredictionNodeFactory 
        extends NodeFactory<LocalUcscPredictionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalUcscPredictionNodeModel createNodeModel() {
        return new LocalUcscPredictionNodeModel();
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
    public NodeView<LocalUcscPredictionNodeModel> createNodeView(final int viewIndex,
            final LocalUcscPredictionNodeModel nodeModel) {
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
        return new LocalUcscPredictionNodeDialog();
    }

}

