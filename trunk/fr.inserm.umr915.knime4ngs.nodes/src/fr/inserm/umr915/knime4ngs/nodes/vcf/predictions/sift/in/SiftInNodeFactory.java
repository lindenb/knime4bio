package fr.inserm.umr915.knime4ngs.nodes.vcf.predictions.sift.in;

import org.knime.base.node.viz.table.TableNodeView;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class SiftInNodeFactory 
        extends NodeFactory<SiftInNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public SiftInNodeModel createNodeModel() {
        return new SiftInNodeModel();
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
    public NodeView<SiftInNodeModel> createNodeView(final int viewIndex,
            final SiftInNodeModel nodeModel)
        {
        throw new IllegalStateException("No view Defined");
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
        return new SiftInNodeDialog();
    }

}

