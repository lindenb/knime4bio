package fr.inserm.umr915.knime4ngs.nodes.vcf.samplepersnp2;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class SamplePerSnpNodeFactory
        extends NodeFactory<SamplePerSnpNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SamplePerSnpNodeModel createNodeModel() {
        return new SamplePerSnpNodeModel();
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
    public NodeView<SamplePerSnpNodeModel> createNodeView(final int viewIndex,
            final SamplePerSnpNodeModel nodeModel)
        {
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
        return new SamplePerSnpNodeDialog();
    }

}

