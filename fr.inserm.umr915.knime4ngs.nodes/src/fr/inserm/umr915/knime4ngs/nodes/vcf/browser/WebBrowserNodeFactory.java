package fr.inserm.umr915.knime4ngs.nodes.vcf.browser;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class WebBrowserNodeFactory 
        extends NodeFactory<WebBrowserNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public WebBrowserNodeModel createNodeModel() {
        return new WebBrowserNodeModel();
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
    public NodeView<WebBrowserNodeModel> createNodeView(final int viewIndex,
            final WebBrowserNodeModel nodeModel) {
        return new WebBrowserNodeView(nodeModel);
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
        return new WebBrowserNodeDialog();
    	}
    
    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Visualizer;
    	}

}

