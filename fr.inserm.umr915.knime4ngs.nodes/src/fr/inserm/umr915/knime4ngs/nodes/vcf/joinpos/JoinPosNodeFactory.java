package fr.inserm.umr915.knime4ngs.nodes.vcf.joinpos;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class JoinPosNodeFactory
        extends NodeFactory<JoinPosNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public JoinPosNodeModel createNodeModel() {
        return new JoinPosNodeModel();
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
    public NodeView<JoinPosNodeModel> createNodeView(final int viewIndex,
            final JoinPosNodeModel nodeModel) {
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
        return new JoinPosNodeDialog();
    }

}

