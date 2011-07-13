package fr.inserm.umr915.knime4ngs.nodes.util.numericcolumnfilter;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class NumColumnFilterNodeFactory
        extends NodeFactory<NumColumnFilterNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NumColumnFilterNodeModel createNodeModel() {
        return new NumColumnFilterNodeModel();
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
    public NodeView<NumColumnFilterNodeModel> createNodeView(final int viewIndex,
            final NumColumnFilterNodeModel nodeModel) {
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
        return new NumColumnFilterNodeDialog();
    }

}

