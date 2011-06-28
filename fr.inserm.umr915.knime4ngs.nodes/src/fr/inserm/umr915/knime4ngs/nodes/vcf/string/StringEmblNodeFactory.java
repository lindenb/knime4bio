package fr.inserm.umr915.knime4ngs.nodes.vcf.string;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class StringEmblNodeFactory
        extends NodeFactory<StringEmblNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public StringEmblNodeModel createNodeModel() {
        return new StringEmblNodeModel();
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
    public NodeView<StringEmblNodeModel> createNodeView(final int viewIndex,
            final StringEmblNodeModel nodeModel)
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
        return new StringEmblNodeDialog();
    }

}

