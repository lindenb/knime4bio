package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.kgview;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class KgViewNodeFactory 
        extends NodeFactory<KgViewNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public KgViewNodeModel createNodeModel() {
        return new KgViewNodeModel();
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
    public NodeView<KgViewNodeModel> createNodeView(final int viewIndex,
            final KgViewNodeModel nodeModel) {
        return new KgViewNodeView(nodeModel);
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
        return new KgViewNodeDialog();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

