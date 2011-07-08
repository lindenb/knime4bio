package fr.inserm.umr915.knime4ngs.nodes.vcf.genscan;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;



/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class GenScanNodeFactory 
        extends NodeFactory<GenScanNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GenScanNodeModel createNodeModel() {
        return new GenScanNodeModel();
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
    public NodeView<GenScanNodeModel> createNodeView(final int viewIndex,
            final GenScanNodeModel nodeModel) {
        return new GenscanNodeView<GenScanNodeModel>(nodeModel);
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
        return new GenScanNodeDialog();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

