package fr.inserm.umr915.knime4ngs.nodes.mw.exists;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * <code>NodeFactory</code> for the "VCFSource" Node.
 * Reads a VCF file
 *
 * @author Pierre Lindenbaum
 */
public class MediaWikiExistsNodeFactory 
        extends NodeFactory<MediaWikiExistsNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaWikiExistsNodeModel createNodeModel() {
        return new MediaWikiExistsNodeModel();
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
    public NodeView<MediaWikiExistsNodeModel> createNodeView(final int viewIndex,
            final MediaWikiExistsNodeModel nodeModel) {
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
        return new MediaWikiExistsNodeDialog();
    }

}

