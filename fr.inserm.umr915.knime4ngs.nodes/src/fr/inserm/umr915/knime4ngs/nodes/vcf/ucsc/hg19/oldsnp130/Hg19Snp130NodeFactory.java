package fr.inserm.umr915.knime4ngs.nodes.vcf.ucsc.hg19.oldsnp130;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


/**
 * @author Pierre Lindenbaum
 */
public class Hg19Snp130NodeFactory
        extends NodeFactory<Hg19Snp130NodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Hg19Snp130NodeModel createNodeModel() {
        return new Hg19Snp130NodeModel();
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
    public NodeView<Hg19Snp130NodeModel> createNodeView(final int viewIndex,
            final Hg19Snp130NodeModel nodeModel) {
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
        return new Hg19Snp130NodeDialog();
    }

}

