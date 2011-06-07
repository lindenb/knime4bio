package fr.inserm.umr915.knime4ngs.nodes.ncbi.view;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class NcbiViewNodeFactory 
        extends NodeFactory<NcbiViewNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public NcbiViewNodeModel createNodeModel() {
        return new NcbiViewNodeModel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<NcbiViewNodeModel> createNodeView(final int viewIndex,
            final NcbiViewNodeModel nodeModel) {
        return new NcbiViewNodeView(nodeModel);
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
    public NodeDialogPane createNodeDialogPane()
    	{
        return new NcbiViewNodeDialog();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

