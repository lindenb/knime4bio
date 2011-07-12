package fr.inserm.umr915.knime4ngs.nodes.vcf.transcript.ucsc;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


@Deprecated
public class UcscTranscriptNodeFactory 
        extends NodeFactory<UcscTranscriptNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public UcscTranscriptNodeModel createNodeModel() {
        return new UcscTranscriptNodeModel();
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
    public NodeView<UcscTranscriptNodeModel> createNodeView(final int viewIndex,
            final UcscTranscriptNodeModel nodeModel) {
        throw new IllegalStateException();
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
    public NodeDialogPane createNodeDialogPane() {
    	throw new IllegalStateException();
    }

}

