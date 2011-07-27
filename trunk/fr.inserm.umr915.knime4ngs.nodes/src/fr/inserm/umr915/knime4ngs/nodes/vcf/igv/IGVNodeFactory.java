package fr.inserm.umr915.knime4ngs.nodes.vcf.igv;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class IGVNodeFactory 
        extends NodeFactory<IGVNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public IGVNodeModel createNodeModel() {
        return new IGVNodeModel();
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
    public NodeView<IGVNodeModel> createNodeView(final int viewIndex,
            final IGVNodeModel nodeModel) {
        return new IGVNodeView(nodeModel);
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
        return new IGVNodeDialog();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

