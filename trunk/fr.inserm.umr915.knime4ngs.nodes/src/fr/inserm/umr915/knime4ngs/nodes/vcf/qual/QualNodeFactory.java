package fr.inserm.umr915.knime4ngs.nodes.vcf.qual;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class QualNodeFactory
        extends NodeFactory<QualNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public QualNodeModel createNodeModel() {
        return new QualNodeModel();
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
    public NodeView<QualNodeModel> createNodeView(final int viewIndex,
            final QualNodeModel nodeModel)
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
        return new QualNodeDialog();
    }

}

