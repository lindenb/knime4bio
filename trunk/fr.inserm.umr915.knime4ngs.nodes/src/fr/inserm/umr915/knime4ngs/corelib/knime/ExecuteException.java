/**
 * 
 */
package fr.inserm.umr915.knime4ngs.corelib.knime;

/**
 * @author lindenb
 *
 */
public class ExecuteException extends Exception
	{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ExecuteException() {
	}

	/**
	 * @param arg0
	 */
	public ExecuteException(String arg0) {
		super(arg0);

	}

	/**
	 * @param arg0
	 */
	public ExecuteException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ExecuteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
