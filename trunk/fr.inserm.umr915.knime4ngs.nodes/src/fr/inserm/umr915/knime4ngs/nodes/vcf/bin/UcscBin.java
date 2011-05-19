package fr.inserm.umr915.knime4ngs.nodes.vcf.bin;

public class UcscBin
	{
	private static int calcBin(
			final int chromStart,
			final int chromEnd,
			int binId,
			int level,
			int binRowStart,
			int rowIndex,
			int binRowCount,
			int genomicPos,
			int genomicLength
			)
			{

			if( 
				chromStart>=genomicPos &&
				chromEnd<= (genomicPos+genomicLength))
				{
				if(level>=MaxLevel) return binId;
				
				int childLength=genomicLength/childrenCount;
				int childBinRowCount=binRowCount*childrenCount;
				int childRowBinStart=binRowStart+binRowCount;
				int firstChildIndex=rowIndex*childrenCount;
				int firstChildBin=childRowBinStart+firstChildIndex;
				for(int i=0;i< childrenCount;++i)
					{
					//System.out.println("i:"+i+"=>"+(firstChildBin+i));
					//System.out.println(" "+(genomicPos+i*childLength)+" <=" +chromStart+" < "+chromEnd+"<"+(genomicPos+(i+1)*childLength));
					int n= calcBin(
							chromStart,
							chromEnd,
							firstChildBin+i,
							level+1,
							childRowBinStart,
							firstChildIndex+i,
							childBinRowCount,
							genomicPos+i*childLength,
							childLength
							);
					if(n!=-1)
						{
						//System.out.println("returning n="+n);
						return n;
						}
					}
				return binId;
				}
			
			return -1;
			}

	
    private static final int childrenCount=8;
	private static final int MaxLevel=4;
	private static final int MaxGenomicLengthLevel=536870912;//2^29
		
	
	/** Given start,end in chromosome coordinates assign it
	 * a bin.   There's a bin for each 128k segment, for each
	 * 1M segment, for each 8M segment, for each 64M segment,
	 * and for each chromosome (which is assumed to be less than
	 * 512M.)  A range goes into the smallest bin it will fit in. */
	public static int getBin(int chromStart,int chromEnd)
		{
		return calcBin(chromStart,chromEnd,0,0,0,0,1,0,MaxGenomicLengthLevel);
		}
	
	}
