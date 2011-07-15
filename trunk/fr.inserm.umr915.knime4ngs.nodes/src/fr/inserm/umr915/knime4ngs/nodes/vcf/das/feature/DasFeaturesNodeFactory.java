package fr.inserm.umr915.knime4ngs.nodes.vcf.das.feature;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class DasFeaturesNodeFactory
        extends NodeFactory<DasFeaturesNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public DasFeaturesNodeModel createNodeModel() {
        return new DasFeaturesNodeModel();
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
    public NodeView<DasFeaturesNodeModel> createNodeView(final int viewIndex,
            final DasFeaturesNodeModel nodeModel) {
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
        return new DasFeaturesNodeDialog();
    }

}

