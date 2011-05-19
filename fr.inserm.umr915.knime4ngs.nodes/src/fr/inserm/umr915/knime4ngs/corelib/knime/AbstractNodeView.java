package fr.inserm.umr915.knime4ngs.corelib.knime;

import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeView;



public class AbstractNodeView<T extends NodeModel> extends NodeView<T>
	{
	public AbstractNodeView(T table)
		{
		super(table);
		}
	
	@Override
	protected void modelChanged() {
		System.err.println("node modelChanged "+getClass());
		}
	@Override
	protected void onClose() {
		System.err.println("node onClose "+getClass());
		}
	@Override
	protected void onOpen() {
		System.err.println("node onOpen "+getClass());
		}
	}
