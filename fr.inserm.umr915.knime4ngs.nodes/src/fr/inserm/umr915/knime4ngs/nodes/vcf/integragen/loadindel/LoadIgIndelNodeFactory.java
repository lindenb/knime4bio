package fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.loadindel;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

import fr.inserm.umr915.knime4ngs.nodes.vcf.integragen.LoadIgNodeDialog;

/**
 * <code>NodeFactory</code> for the "VCFLoader" Node.
 * Loads a VCF File
 *
 * @author Pierre Lindenbaum
 */
public class LoadIgIndelNodeFactory 
        extends NodeFactory<LoadIgIndelNodeModel>
	{

    /**
     * {@inheritDoc}
     */
    @Override
    public LoadIgIndelNodeModel createNodeModel() {
        return new LoadIgIndelNodeModel();
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
    public NodeView<LoadIgIndelNodeModel> createNodeView(final int viewIndex,
            final LoadIgIndelNodeModel nodeModel) {
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
        return new LoadIgNodeDialog();
    }

}

