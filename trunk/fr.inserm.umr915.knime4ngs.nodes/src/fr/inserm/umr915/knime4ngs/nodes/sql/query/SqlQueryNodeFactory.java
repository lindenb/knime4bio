package fr.inserm.umr915.knime4ngs.nodes.sql.query;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SqlQueryNodeFactory
        extends NodeFactory<SqlQueryNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SqlQueryNodeModel createNodeModel() {
        return new SqlQueryNodeModel();
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
    public NodeView<SqlQueryNodeModel> createNodeView(final int viewIndex,
            final SqlQueryNodeModel nodeModel) {
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
        return new SqlQueryNodeDialog();
    }

}

