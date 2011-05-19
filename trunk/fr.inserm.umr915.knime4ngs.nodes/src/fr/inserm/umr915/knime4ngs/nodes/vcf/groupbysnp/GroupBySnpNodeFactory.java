package fr.inserm.umr915.knime4ngs.nodes.vcf.groupbysnp;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class GroupBySnpNodeFactory
        extends NodeFactory<GroupBySnpNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupBySnpNodeModel createNodeModel() {
        return new GroupBySnpNodeModel();
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
    public NodeView<GroupBySnpNodeModel> createNodeView(final int viewIndex,
            final GroupBySnpNodeModel nodeModel)
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
        return new GroupBySnpNodeDialog();
    }

}

