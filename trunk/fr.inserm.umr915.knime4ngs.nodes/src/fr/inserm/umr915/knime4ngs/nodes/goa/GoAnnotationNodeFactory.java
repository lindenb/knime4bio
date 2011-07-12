package fr.inserm.umr915.knime4ngs.nodes.goa;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;


@Deprecated
public class GoAnnotationNodeFactory
        extends NodeFactory<GoAnnotationNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public GoAnnotationNodeModel createNodeModel() {
        return new GoAnnotationNodeModel();
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
    public NodeView<GoAnnotationNodeModel> createNodeView(final int viewIndex,
            final GoAnnotationNodeModel nodeModel)
        {
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
        return new GoAnnotationNodeDialog();
    }

    @Override
    public org.knime.core.node.NodeFactory.NodeType getType() {
    	return NodeType.Source;
    	}
}

