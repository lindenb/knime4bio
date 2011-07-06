package fr.inserm.umr915.knime4ngs.nodes.vcf.cut;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class CutRegionNodeFactory
        extends NodeFactory<CutRegionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CutRegionNodeModel createNodeModel() {
        return new CutRegionNodeModel();
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
    public NodeView<CutRegionNodeModel> createNodeView(final int viewIndex,
            final CutRegionNodeModel nodeModel)
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
        return new CutRegionNodeDialog();
    }

}

