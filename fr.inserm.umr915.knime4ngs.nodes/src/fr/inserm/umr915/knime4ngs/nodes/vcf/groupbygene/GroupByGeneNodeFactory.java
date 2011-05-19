package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbygene;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class GroupByGeneNodeFactory
        extends NodeFactory<GroupByGeneNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupByGeneNodeModel createNodeModel() {
        return new GroupByGeneNodeModel();
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
    public NodeView<GroupByGeneNodeModel> createNodeView(final int viewIndex,
            final GroupByGeneNodeModel nodeModel)
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
        return new GroupByGeneNodeDialog();
    }

}

