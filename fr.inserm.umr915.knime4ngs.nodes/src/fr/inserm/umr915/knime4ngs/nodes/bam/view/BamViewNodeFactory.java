package fr.inserm.umr915.knime4ngs.nodes.bam.view;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class BamViewNodeFactory 
        extends NodeFactory<BamViewNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public BamViewNodeModel createNodeModel() {
        return new BamViewNodeModel();
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
    public NodeView<BamViewNodeModel> createNodeView(final int viewIndex,
            final BamViewNodeModel nodeModel) {
        return new BamViewNodeView(nodeModel);
    	}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane()
    	{
        throw new IllegalStateException();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

