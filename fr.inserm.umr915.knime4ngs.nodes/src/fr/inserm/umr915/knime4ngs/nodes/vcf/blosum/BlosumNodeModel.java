package fr.inserm.umr915.knime4ngs.nodes.vcf.blosum;


import java.util.ArrayList;
import java.util.List;


import org.knime.base.data.append.column.AppendedColumnRow;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.container.CloseableRowIterator;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.defaultnodesettings.SettingsModel;
import org.knime.core.node.defaultnodesettings.SettingsModelColumnName;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

import fr.inserm.umr915.knime4ngs.nodes.vcf.AbstractVCFNodeModel;




/**

 * @author Pierre Lindenbaum
 */
public class BlosumNodeModel extends AbstractVCFNodeModel
	{
	static final String AA1_PROPERTY="first.aa.col";
	static final String AA1_DEFAULT="AA1";
	private final SettingsModelColumnName m_aa1 =
        new SettingsModelColumnName(AA1_PROPERTY,AA1_DEFAULT);
	
	
	static final String AA2_PROPERTY="second.aa.col";
	static final String AA2_DEFAULT="AA2";
	private final SettingsModelColumnName m_aa2 =
        new SettingsModelColumnName(AA2_PROPERTY,AA2_DEFAULT);

	static final String MATRIX_PROPERTY="matrix";
	static final String MATRIX_DEFAULT="blosum62";
	static final String ALL_MATRIX[]=new String[]{
	    	"blosum90",
	    	"blosum80",
	    	"blosum62",
	    	"blosum50",
	    	"blosum45"
	    	};
	private final SettingsModelString m_matrix =
        new SettingsModelString(MATRIX_PROPERTY,MATRIX_DEFAULT);
	
	public static abstract class ScoreMatrix
	    {
		public int aa2index(char aa)
			{
			switch(Character.toUpperCase(aa))
				{
				case 'A' : return 0;
				case 'R' : return 1;
				case 'N' : return 2;
				case 'D' : return 3;
				case 'C' : return 4;
				case 'Q' : return 5;
				case 'E' : return 6;
				case 'G' : return 7;
				case 'H' : return 8;
				case 'I' : return 9;
				case 'L' : return 10;
				case 'K' : return 11;
				case 'M' : return 12;
				case 'F' : return 13;
				case 'P' : return 14;
				case 'S' : return 15;
				case 'T' : return 16;
				case 'W' : return 17;
				case 'Y' : return 18;
				case 'V' : return 19;
				case 'B' : return 20;
				case 'J' : return 21;
				case 'Z' : return 22;
				case 'X' : return 23;
				default: return 24;
				}
			}
		public abstract int[][] getMatrix();
	    public int getScore(char aa1,char aa2)
	    	{
	    	int matrice[][]=getMatrix();
	    	int i=aa2index(aa1);
	    	int j=aa2index(aa1);
	    	return matrice[i][j];
	    	}
	    }
	
	private static class Blosum90 extends ScoreMatrix
		{
		private int matrix[][]=new int[][]{
				new int[]{5,-2,-2,-3,-1,-1,-1,0,-2,-2,-2,-1,-2,-3,-1,1,0,-4,-3,-1,-2,-2,-1,-1,-6},
				new int[]{-2,6,-1,-3,-5,1,-1,-3,0,-4,-3,2,-2,-4,-3,-1,-2,-4,-3,-3,-2,-3,0,-1,-6},
				new int[]{-2,-1,7,1,-4,0,-1,-1,0,-4,-4,0,-3,-4,-3,0,0,-5,-3,-4,5,-4,-1,-1,-6},
				new int[]{-3,-3,1,7,-5,-1,1,-2,-2,-5,-5,-1,-4,-5,-3,-1,-2,-6,-4,-5,5,-5,1,-1,-6},
				new int[]{-1,-5,-4,-5,9,-4,-6,-4,-5,-2,-2,-4,-2,-3,-4,-2,-2,-4,-4,-2,-4,-2,-5,-1,-6},
				new int[]{-1,1,0,-1,-4,7,2,-3,1,-4,-3,1,0,-4,-2,-1,-1,-3,-3,-3,-1,-3,5,-1,-6},
				new int[]{-1,-1,-1,1,-6,2,6,-3,-1,-4,-4,0,-3,-5,-2,-1,-1,-5,-4,-3,1,-4,5,-1,-6},
				new int[]{0,-3,-1,-2,-4,-3,-3,6,-3,-5,-5,-2,-4,-5,-3,-1,-3,-4,-5,-5,-2,-5,-3,-1,-6},
				new int[]{-2,0,0,-2,-5,1,-1,-3,8,-4,-4,-1,-3,-2,-3,-2,-2,-3,1,-4,-1,-4,0,-1,-6},
				new int[]{-2,-4,-4,-5,-2,-4,-4,-5,-4,5,1,-4,1,-1,-4,-3,-1,-4,-2,3,-5,3,-4,-1,-6},
				new int[]{-2,-3,-4,-5,-2,-3,-4,-5,-4,1,5,-3,2,0,-4,-3,-2,-3,-2,0,-5,4,-4,-1,-6},
				new int[]{-1,2,0,-1,-4,1,0,-2,-1,-4,-3,6,-2,-4,-2,-1,-1,-5,-3,-3,-1,-3,1,-1,-6},
				new int[]{-2,-2,-3,-4,-2,0,-3,-4,-3,1,2,-2,7,-1,-3,-2,-1,-2,-2,0,-4,2,-2,-1,-6},
				new int[]{-3,-4,-4,-5,-3,-4,-5,-5,-2,-1,0,-4,-1,7,-4,-3,-3,0,3,-2,-4,0,-4,-1,-6},
				new int[]{-1,-3,-3,-3,-4,-2,-2,-3,-3,-4,-4,-2,-3,-4,8,-2,-2,-5,-4,-3,-3,-4,-2,-1,-6},
				new int[]{1,-1,0,-1,-2,-1,-1,-1,-2,-3,-3,-1,-2,-3,-2,5,1,-4,-3,-2,0,-3,-1,-1,-6},
				new int[]{0,-2,0,-2,-2,-1,-1,-3,-2,-1,-2,-1,-1,-3,-2,1,6,-4,-2,-1,-1,-2,-1,-1,-6},
				new int[]{-4,-4,-5,-6,-4,-3,-5,-4,-3,-4,-3,-5,-2,0,-5,-4,-4,11,2,-3,-6,-3,-4,-1,-6},
				new int[]{-3,-3,-3,-4,-4,-3,-4,-5,1,-2,-2,-3,-2,3,-4,-3,-2,2,8,-3,-4,-2,-3,-1,-6},
				new int[]{-1,-3,-4,-5,-2,-3,-3,-5,-4,3,0,-3,0,-2,-3,-2,-1,-3,-3,5,-4,1,-3,-1,-6},
				new int[]{-2,-2,5,5,-4,-1,1,-2,-1,-5,-5,-1,-4,-4,-3,0,-1,-6,-4,-4,5,-5,0,-1,-6},
				new int[]{-2,-3,-4,-5,-2,-3,-4,-5,-4,3,4,-3,2,0,-4,-3,-2,-3,-2,1,-5,4,-4,-1,-6},
				new int[]{-1,0,-1,1,-5,5,5,-3,0,-4,-4,1,-2,-4,-2,-1,-1,-4,-3,-3,0,-4,5,-1,-6},
				new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6},
				new int[]{-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,1}
				};
		public int[][] getMatrix()
			{
			return this.matrix;
			}
		}
	
	
	private static class Blosum80 extends ScoreMatrix
		{
		private int matrix[][]=new int[][]{
				new int[]{5,-2,-2,-2,-1,-1,-1,0,-2,-2,-2,-1,-1,-3,-1,1,0,-3,-2,0,-2,-2,-1,-1,-6},
				new int[]{-2,6,-1,-2,-4,1,-1,-3,0,-3,-3,2,-2,-4,-2,-1,-1,-4,-3,-3,-1,-3,0,-1,-6},
				new int[]{-2,-1,6,1,-3,0,-1,-1,0,-4,-4,0,-3,-4,-3,0,0,-4,-3,-4,5,-4,0,-1,-6},
				new int[]{-2,-2,1,6,-4,-1,1,-2,-2,-4,-5,-1,-4,-4,-2,-1,-1,-6,-4,-4,5,-5,1,-1,-6},
				new int[]{-1,-4,-3,-4,9,-4,-5,-4,-4,-2,-2,-4,-2,-3,-4,-2,-1,-3,-3,-1,-4,-2,-4,-1,-6},
				new int[]{-1,1,0,-1,-4,6,2,-2,1,-3,-3,1,0,-4,-2,0,-1,-3,-2,-3,0,-3,4,-1,-6},
				new int[]{-1,-1,-1,1,-5,2,6,-3,0,-4,-4,1,-2,-4,-2,0,-1,-4,-3,-3,1,-4,5,-1,-6},
				new int[]{0,-3,-1,-2,-4,-2,-3,6,-3,-5,-4,-2,-4,-4,-3,-1,-2,-4,-4,-4,-1,-5,-3,-1,-6},
				new int[]{-2,0,0,-2,-4,1,0,-3,8,-4,-3,-1,-2,-2,-3,-1,-2,-3,2,-4,-1,-4,0,-1,-6},
				new int[]{-2,-3,-4,-4,-2,-3,-4,-5,-4,5,1,-3,1,-1,-4,-3,-1,-3,-2,3,-4,3,-4,-1,-6},
				new int[]{-2,-3,-4,-5,-2,-3,-4,-4,-3,1,4,-3,2,0,-3,-3,-2,-2,-2,1,-4,3,-3,-1,-6},
				new int[]{-1,2,0,-1,-4,1,1,-2,-1,-3,-3,5,-2,-4,-1,-1,-1,-4,-3,-3,-1,-3,1,-1,-6},
				new int[]{-1,-2,-3,-4,-2,0,-2,-4,-2,1,2,-2,6,0,-3,-2,-1,-2,-2,1,-3,2,-1,-1,-6},
				new int[]{-3,-4,-4,-4,-3,-4,-4,-4,-2,-1,0,-4,0,6,-4,-3,-2,0,3,-1,-4,0,-4,-1,-6},
				new int[]{-1,-2,-3,-2,-4,-2,-2,-3,-3,-4,-3,-1,-3,-4,8,-1,-2,-5,-4,-3,-2,-4,-2,-1,-6},
				new int[]{1,-1,0,-1,-2,0,0,-1,-1,-3,-3,-1,-2,-3,-1,5,1,-4,-2,-2,0,-3,0,-1,-6},
				new int[]{0,-1,0,-1,-1,-1,-1,-2,-2,-1,-2,-1,-1,-2,-2,1,5,-4,-2,0,-1,-1,-1,-1,-6},
				new int[]{-3,-4,-4,-6,-3,-3,-4,-4,-3,-3,-2,-4,-2,0,-5,-4,-4,11,2,-3,-5,-3,-3,-1,-6},
				new int[]{-2,-3,-3,-4,-3,-2,-3,-4,2,-2,-2,-3,-2,3,-4,-2,-2,2,7,-2,-3,-2,-3,-1,-6},
				new int[]{0,-3,-4,-4,-1,-3,-3,-4,-4,3,1,-3,1,-1,-3,-2,0,-3,-2,4,-4,2,-3,-1,-6},
				new int[]{-2,-1,5,5,-4,0,1,-1,-1,-4,-4,-1,-3,-4,-2,0,-1,-5,-3,-4,5,-4,0,-1,-6},
				new int[]{-2,-3,-4,-5,-2,-3,-4,-5,-4,3,3,-3,2,0,-4,-3,-1,-3,-2,2,-4,3,-3,-1,-6},
				new int[]{-1,0,0,1,-4,4,5,-3,0,-4,-3,1,-1,-4,-2,0,-1,-3,-3,-3,0,-3,5,-1,-6},
				new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-6},
				new int[]{-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,1}
				};
		public int[][] getMatrix()
			{
			return this.matrix;
			}
		}
	
	/*
	 *  curl  "http://www.ncbi.nlm.nih.gov/Class/FieldGuide/BLOSUM62.txt" | grep -v "#" | grep -v " A  R  N "  | tr -s " " | cut -c 3- | tr " " "," | sed -e 's/^/new int[]{/' -e 's/,$/},/'
	 */
	private static class Blosum62 extends ScoreMatrix
		{
		private int matrix[][]=new int[][]{
				new int[]{4,-1,-2,-2,0,-1,-1,0,-2,-1,-1,-1,-1,-2,-1,1,0,-3,-2,0,-2,-1,-1,-1,-4},
				new int[]{-1,5,0,-2,-3,1,0,-2,0,-3,-2,2,-1,-3,-2,-1,-1,-3,-2,-3,-1,-2,0,-1,-4},
				new int[]{-2,0,6,1,-3,0,0,0,1,-3,-3,0,-2,-3,-2,1,0,-4,-2,-3,4,-3,0,-1,-4},
				new int[]{-2,-2,1,6,-3,0,2,-1,-1,-3,-4,-1,-3,-3,-1,0,-1,-4,-3,-3,4,-3,1,-1,-4},
				new int[]{0,-3,-3,-3,9,-3,-4,-3,-3,-1,-1,-3,-1,-2,-3,-1,-1,-2,-2,-1,-3,-1,-3,-1,-4},
				new int[]{-1,1,0,0,-3,5,2,-2,0,-3,-2,1,0,-3,-1,0,-1,-2,-1,-2,0,-2,4,-1,-4},
				new int[]{-1,0,0,2,-4,2,5,-2,0,-3,-3,1,-2,-3,-1,0,-1,-3,-2,-2,1,-3,4,-1,-4},
				new int[]{0,-2,0,-1,-3,-2,-2,6,-2,-4,-4,-2,-3,-3,-2,0,-2,-2,-3,-3,-1,-4,-2,-1,-4},
				new int[]{-2,0,1,-1,-3,0,0,-2,8,-3,-3,-1,-2,-1,-2,-1,-2,-2,2,-3,0,-3,0,-1,-4},
				new int[]{-1,-3,-3,-3,-1,-3,-3,-4,-3,4,2,-3,1,0,-3,-2,-1,-3,-1,3,-3,3,-3,-1,-4},
				new int[]{-1,-2,-3,-4,-1,-2,-3,-4,-3,2,4,-2,2,0,-3,-2,-1,-2,-1,1,-4,3,-3,-1,-4},
				new int[]{-1,2,0,-1,-3,1,1,-2,-1,-3,-2,5,-1,-3,-1,0,-1,-3,-2,-2,0,-3,1,-1,-4},
				new int[]{-1,-1,-2,-3,-1,0,-2,-3,-2,1,2,-1,5,0,-2,-1,-1,-1,-1,1,-3,2,-1,-1,-4},
				new int[]{-2,-3,-3,-3,-2,-3,-3,-3,-1,0,0,-3,0,6,-4,-2,-2,1,3,-1,-3,0,-3,-1,-4},
				new int[]{-1,-2,-2,-1,-3,-1,-1,-2,-2,-3,-3,-1,-2,-4,7,-1,-1,-4,-3,-2,-2,-3,-1,-1,-4},
				new int[]{1,-1,1,0,-1,0,0,0,-1,-2,-2,0,-1,-2,-1,4,1,-3,-2,-2,0,-2,0,-1,-4},
				new int[]{0,-1,0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1,1,5,-2,-2,0,-1,-1,-1,-1,-4},
				new int[]{-3,-3,-4,-4,-2,-2,-3,-2,-2,-3,-2,-3,-1,1,-4,-3,-2,11,2,-3,-4,-2,-2,-1,-4},
				new int[]{-2,-2,-2,-3,-2,-1,-2,-3,2,-1,-1,-2,-1,3,-3,-2,-2,2,7,-1,-3,-1,-2,-1,-4},
				new int[]{0,-3,-3,-3,-1,-2,-2,-3,-3,3,1,-2,1,-1,-2,-2,0,-3,-1,4,-3,2,-2,-1,-4},
				new int[]{-2,-1,4,4,-3,0,1,-1,0,-3,-4,0,-3,-3,-2,0,-1,-4,-3,-3,4,-3,0,-1,-4},
				new int[]{-1,-2,-3,-3,-1,-2,-3,-4,-3,3,3,-3,2,0,-3,-2,-1,-2,-1,2,-3,3,-3,-1,-4},
				new int[]{-1,0,0,1,-3,4,4,-2,0,-3,-3,1,-1,-3,-1,0,-1,-2,-2,-2,0,-3,4,-1,-4},
				new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-4},
				new int[]{-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,1}
			};
		
		public int[][] getMatrix()
			{
			return this.matrix;
			}
		}
	
	
	private static class Blosum50 extends ScoreMatrix
		{
		private int matrix[][]=new int[][]{
			new int[]{5,-2,-1,-2,-1,-1,-1,0,-2,-1,-2,-1,-1,-3,-1,1,0,-3,-2,0,-2,-2,-1,-1,-5},
			new int[]{-2,7,-1,-2,-4,1,0,-3,0,-4,-3,3,-2,-3,-3,-1,-1,-3,-1,-3,-1,-3,0,-1,-5},
			new int[]{-1,-1,7,2,-2,0,0,0,1,-3,-4,0,-2,-4,-2,1,0,-4,-2,-3,5,-4,0,-1,-5},
			new int[]{-2,-2,2,8,-4,0,2,-1,-1,-4,-4,-1,-4,-5,-1,0,-1,-5,-3,-4,6,-4,1,-1,-5},
			new int[]{-1,-4,-2,-4,13,-3,-3,-3,-3,-2,-2,-3,-2,-2,-4,-1,-1,-5,-3,-1,-3,-2,-3,-1,-5},
			new int[]{-1,1,0,0,-3,7,2,-2,1,-3,-2,2,0,-4,-1,0,-1,-1,-1,-3,0,-3,4,-1,-5},
			new int[]{-1,0,0,2,-3,2,6,-3,0,-4,-3,1,-2,-3,-1,-1,-1,-3,-2,-3,1,-3,5,-1,-5},
			new int[]{0,-3,0,-1,-3,-2,-3,8,-2,-4,-4,-2,-3,-4,-2,0,-2,-3,-3,-4,-1,-4,-2,-1,-5},
			new int[]{-2,0,1,-1,-3,1,0,-2,10,-4,-3,0,-1,-1,-2,-1,-2,-3,2,-4,0,-3,0,-1,-5},
			new int[]{-1,-4,-3,-4,-2,-3,-4,-4,-4,5,2,-3,2,0,-3,-3,-1,-3,-1,4,-4,4,-3,-1,-5},
			new int[]{-2,-3,-4,-4,-2,-2,-3,-4,-3,2,5,-3,3,1,-4,-3,-1,-2,-1,1,-4,4,-3,-1,-5},
			new int[]{-1,3,0,-1,-3,2,1,-2,0,-3,-3,6,-2,-4,-1,0,-1,-3,-2,-3,0,-3,1,-1,-5},
			new int[]{-1,-2,-2,-4,-2,0,-2,-3,-1,2,3,-2,7,0,-3,-2,-1,-1,0,1,-3,2,-1,-1,-5},
			new int[]{-3,-3,-4,-5,-2,-4,-3,-4,-1,0,1,-4,0,8,-4,-3,-2,1,4,-1,-4,1,-4,-1,-5},
			new int[]{-1,-3,-2,-1,-4,-1,-1,-2,-2,-3,-4,-1,-3,-4,10,-1,-1,-4,-3,-3,-2,-3,-1,-1,-5},
			new int[]{1,-1,1,0,-1,0,-1,0,-1,-3,-3,0,-2,-3,-1,5,2,-4,-2,-2,0,-3,0,-1,-5},
			new int[]{0,-1,0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1,2,5,-3,-2,0,0,-1,-1,-1,-5},
			new int[]{-3,-3,-4,-5,-5,-1,-3,-3,-3,-3,-2,-3,-1,1,-4,-4,-3,15,2,-3,-5,-2,-2,-1,-5},
			new int[]{-2,-1,-2,-3,-3,-1,-2,-3,2,-1,-1,-2,0,4,-3,-2,-2,2,8,-1,-3,-1,-2,-1,-5},
			new int[]{0,-3,-3,-4,-1,-3,-3,-4,-4,4,1,-3,1,-1,-3,-2,0,-3,-1,5,-3,2,-3,-1,-5},
			new int[]{-2,-1,5,6,-3,0,1,-1,0,-4,-4,0,-3,-4,-2,0,0,-5,-3,-3,6,-4,1,-1,-5},
			new int[]{-2,-3,-4,-4,-2,-3,-3,-4,-3,4,4,-3,2,1,-3,-3,-1,-2,-1,2,-4,4,-3,-1,-5},
			new int[]{-1,0,0,1,-3,4,5,-2,0,-3,-3,1,-1,-4,-1,0,-1,-2,-2,-3,1,-3,5,-1,-5},
			new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-5},
			new int[]{-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,1}
			};
		
		public int[][] getMatrix()
			{
			return this.matrix;
			}
		}
	
	private static class Blosum45 extends ScoreMatrix
		{
		private int matrix[][]=new int[][]{
				new int[]{5,-2,-1,-2,-1,-1,-1,0,-2,-1,-1,-1,-1,-2,-1,1,0,-2,-2,0,-1,-1,-1,-1,-5},
				new int[]{-2,7,0,-1,-3,1,0,-2,0,-3,-2,3,-1,-2,-2,-1,-1,-2,-1,-2,-1,-3,1,-1,-5},
				new int[]{-1,0,6,2,-2,0,0,0,1,-2,-3,0,-2,-2,-2,1,0,-4,-2,-3,5,-3,0,-1,-5},
				new int[]{-2,-1,2,7,-3,0,2,-1,0,-4,-3,0,-3,-4,-1,0,-1,-4,-2,-3,6,-3,1,-1,-5},
				new int[]{-1,-3,-2,-3,12,-3,-3,-3,-3,-3,-2,-3,-2,-2,-4,-1,-1,-5,-3,-1,-2,-2,-3,-1,-5},
				new int[]{-1,1,0,0,-3,6,2,-2,1,-2,-2,1,0,-4,-1,0,-1,-2,-1,-3,0,-2,4,-1,-5},
				new int[]{-1,0,0,2,-3,2,6,-2,0,-3,-2,1,-2,-3,0,0,-1,-3,-2,-3,1,-3,5,-1,-5},
				new int[]{0,-2,0,-1,-3,-2,-2,7,-2,-4,-3,-2,-2,-3,-2,0,-2,-2,-3,-3,-1,-4,-2,-1,-5},
				new int[]{-2,0,1,0,-3,1,0,-2,10,-3,-2,-1,0,-2,-2,-1,-2,-3,2,-3,0,-2,0,-1,-5},
				new int[]{-1,-3,-2,-4,-3,-2,-3,-4,-3,5,2,-3,2,0,-2,-2,-1,-2,0,3,-3,4,-3,-1,-5},
				new int[]{-1,-2,-3,-3,-2,-2,-2,-3,-2,2,5,-3,2,1,-3,-3,-1,-2,0,1,-3,4,-2,-1,-5},
				new int[]{-1,3,0,0,-3,1,1,-2,-1,-3,-3,5,-1,-3,-1,-1,-1,-2,-1,-2,0,-3,1,-1,-5},
				new int[]{-1,-1,-2,-3,-2,0,-2,-2,0,2,2,-1,6,0,-2,-2,-1,-2,0,1,-2,2,-1,-1,-5},
				new int[]{-2,-2,-2,-4,-2,-4,-3,-3,-2,0,1,-3,0,8,-3,-2,-1,1,3,0,-3,1,-3,-1,-5},
				new int[]{-1,-2,-2,-1,-4,-1,0,-2,-2,-2,-3,-1,-2,-3,9,-1,-1,-3,-3,-3,-2,-3,-1,-1,-5},
				new int[]{1,-1,1,0,-1,0,0,0,-1,-2,-3,-1,-2,-2,-1,4,2,-4,-2,-1,0,-2,0,-1,-5},
				new int[]{0,-1,0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-1,-1,2,5,-3,-1,0,0,-1,-1,-1,-5},
				new int[]{-2,-2,-4,-4,-5,-2,-3,-2,-3,-2,-2,-2,-2,1,-3,-4,-3,15,3,-3,-4,-2,-2,-1,-5},
				new int[]{-2,-1,-2,-2,-3,-1,-2,-3,2,0,0,-1,0,3,-3,-2,-1,3,8,-1,-2,0,-2,-1,-5},
				new int[]{0,-2,-3,-3,-1,-3,-3,-3,-3,3,1,-2,1,0,-3,-1,0,-3,-1,5,-3,2,-3,-1,-5},
				new int[]{-1,-1,5,6,-2,0,1,-1,0,-3,-3,0,-2,-3,-2,0,0,-4,-2,-3,5,-3,1,-1,-5},
				new int[]{-1,-3,-3,-3,-2,-2,-3,-4,-2,4,4,-3,2,1,-3,-2,-1,-2,0,2,-3,4,-2,-1,-5},
				new int[]{-1,1,0,1,-3,4,5,-2,0,-3,-2,1,-1,-3,-1,0,-1,-2,-2,-3,1,-2,5,-1,-5},
				new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-5},
				new int[]{-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,1}
			};
		
		public int[][] getMatrix()
			{
			return this.matrix;
			}
		}
	
	
    /**
     * Constructor for the node model.
     */
    protected BlosumNodeModel()
    	{
        super(1,1);
    	}
   
    private  static ScoreMatrix getScoreMatrix(String name)
    	{
    	if(name.equals("blosum90")) return new Blosum90();
    	if(name.equals("blosum80")) return new Blosum80();
    	if(name.equals("blosum62")) return new Blosum62();
    	if(name.equals("blosum50")) return new Blosum50();
    	if(name.equals("blosum45")) return new Blosum45();
    	return new Blosum62();
    	}
   
    
    private DataTableSpec createDataTableSpec()
    	{
    	return new DataTableSpec(new DataColumnSpec[]{
    		new DataColumnSpecCreator(this.m_matrix.getStringValue(), IntCell.TYPE).createSpec()	
    		});
    	}
    
    private static Character getAA(DataCell c)
    	{
    	if(c==null || c.isMissing()) return null;
    	if(!(c instanceof StringCell )) return null;
    	String s=StringCell.class.cast(c).getStringValue().trim().toUpperCase();
    	if(s.length()!=1) return null;
    	if(! Character.isLetter(s.charAt(0))) return null;
    	return s.charAt(0);
    	}
    
    @Override
    protected BufferedDataTable[] execute(
    		final BufferedDataTable[] inData,
            final ExecutionContext exec
            ) throws Exception
            {
			BufferedDataContainer container1=null;
			BufferedDataTable inTable=inData[0];
			DataTableSpec inSpec= inTable.getDataTableSpec();
			int aa1Col= findColumnIndex(inSpec,m_aa1,StringCell.TYPE);
			int aa2Col= findColumnIndex(inSpec,m_aa2,StringCell.TYPE);
			ScoreMatrix matrix =getScoreMatrix(this.m_matrix.getStringValue());
			
			
			try
		    	{
		       
		        container1 = exec.createDataContainer(new DataTableSpec(inSpec,createDataTableSpec()));

		        double total=inTable.getRowCount();
		        int nRow=0;
		        CloseableRowIterator iter=null;
		        try {
		        	iter=inTable.iterator();
		        	while(iter.hasNext())
		        		{
		        		++nRow;
		        		DataRow row=iter.next();
		        		DataCell cell1= row.getCell(aa1Col);
		        		DataCell cell2= row.getCell(aa2Col);
		        		DataCell score=DataType.getMissingCell();
		        		if(getAA(cell1)!=null && getAA(cell2)!=null)
		        			{
		        			score=new IntCell(matrix.getScore(getAA(cell1),getAA(cell2)));
		        			}
		        		
						row=new AppendedColumnRow(row,score);
						container1.addRowToTable(row);
		        		exec.checkCanceled();
		            	exec.setProgress(nRow/total,"Blosum");
		        		}
					} 
		        catch (Exception e)
					{
					throw e;
					}
				finally
					{
					safeClose(iter);
					}
		        
				// once we are done, we close the container and return its table
		        container1.close();
		        BufferedDataTable out1 = container1.getTable();
		        container1=null;
		        
		        BufferedDataTable array[]= new BufferedDataTable[]{out1};
		    	return array;
		    	}
		catch(Exception err)
			{
			getLogger().error("Boum", err);
			err.printStackTrace();
			throw err;
			}
		finally
			{
			safeClose(container1);
			}
       }
    
    
    @Override
    protected DataTableSpec[] configure(DataTableSpec[] inSpecs)
    		throws InvalidSettingsException {
    	if(inSpecs==null || inSpecs.length!=1)
    		{
    		throw new InvalidSettingsException("Expected one table");
    		}
    	
    	DataTableSpec in=inSpecs[0];
    	findColumnIndex(in,m_aa1,StringCell.TYPE);
    	findColumnIndex(in,m_aa2,StringCell.TYPE);
    	return new DataTableSpec[]{new DataTableSpec(in,createDataTableSpec())};
    	}
    
    @Override
    protected List<SettingsModel> getSettingsModel() {
    	List<SettingsModel> L=new ArrayList<SettingsModel>( super.getSettingsModel());
    	L.add(this.m_aa1);
    	L.add(this.m_aa2);
    	L.add(this.m_matrix);
    	return L;
    	}
    
    
    
	}

