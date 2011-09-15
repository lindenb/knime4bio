package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.loadsnp;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class LoadIgSnpNodeFactory 
        extends NodeFactory<LoadIgSnpNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadIgSnpNodeModel createNodeModel() {
        return new LoadIgSnpNodeModel();
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
    public NodeView<LoadIgSnpNodeModel> createNodeView(final int viewIndex,
            final LoadIgSnpNodeModel nodeModel) {
        throw new IllegalStateException();
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
        return new LoadIgSnpNodeDialog();
    }

}

