package fr.inserm.umr915.knime4ngs.nodes.vcf.go;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class GoNodeFactory
        extends NodeFactory<GoNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoNodeModel createNodeModel() {
        return new GoNodeModel();
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
    public NodeView<GoNodeModel> createNodeView(final int viewIndex,
            final GoNodeModel nodeModel)
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
        return new GoNodeDialog();
    }

}

