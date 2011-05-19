package fr.inserm.umr915.knime4ngs.nodes.vcf.region;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class RegionNodeFactory
        extends NodeFactory<RegionNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RegionNodeModel createNodeModel() {
        return new RegionNodeModel();
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
    public NodeView<RegionNodeModel> createNodeView(final int viewIndex,
            final RegionNodeModel nodeModel)
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
        return new RegionNodeDialog();
    }

}

