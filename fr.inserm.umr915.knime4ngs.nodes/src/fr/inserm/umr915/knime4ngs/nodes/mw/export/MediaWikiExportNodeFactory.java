package fr.inserm.umr915.knime4ngs.nodes.mw.export;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class MediaWikiExportNodeFactory 
        extends NodeFactory<MediaWikiExportNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaWikiExportNodeModel createNodeModel() {
        return new MediaWikiExportNodeModel();
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
    public NodeView<MediaWikiExportNodeModel> createNodeView(final int viewIndex,
            final MediaWikiExportNodeModel nodeModel) {
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
        return new MediaWikiExportNodeDialog();
    }

}

