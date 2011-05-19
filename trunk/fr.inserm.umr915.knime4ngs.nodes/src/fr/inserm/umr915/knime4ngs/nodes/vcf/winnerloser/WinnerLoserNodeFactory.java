package fr.inserm.umr915.knime4ngs.nodes.vcf.winnerloser;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class WinnerLoserNodeFactory
        extends NodeFactory<WinnerLoserNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public WinnerLoserNodeModel createNodeModel() {
        return new WinnerLoserNodeModel();
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
    public NodeView<WinnerLoserNodeModel> createNodeView(final int viewIndex,
            final WinnerLoserNodeModel nodeModel)
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
        return new WinnerLoserNodeDialog();
    }

}

