package fr.inserm.umr915.knime4ngs.nodes.vcf.alts2alt;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class AltsToAltNodeFactory
        extends NodeFactory<AltsToAltNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public AltsToAltNodeModel createNodeModel() {
        return new AltsToAltNodeModel();
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
    public NodeView<AltsToAltNodeModel> createNodeView(final int viewIndex,
            final AltsToAltNodeModel nodeModel)
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
        return new AltsToAltNodeDialog();
    }

}

