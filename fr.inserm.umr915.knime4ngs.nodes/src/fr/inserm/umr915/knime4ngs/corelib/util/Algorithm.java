package fr.inserm.umr915.knime4ngs.corelib.util;

import java.util.Comparator;
import java.util.List;

public class Algorithm
	{
	public static <T >int lowerBound(
		List<T> array,	
		int first,
		int last,
		T L,
		Comparator<T> comparator
		)
		{
	    int len = last - first;
	    while (len > 0)
	            {
	            int half = len / 2;
	            int middle = first + half;
	
	            if ( comparator.compare(array.get(middle),L) < 0  )
	                    {
	                    first = middle + 1;
	                    len -= half + 1;
	                    }
	            else
	                    {
	                    len = half;
	                    }
	            }
	    return first;
		}
	
	public static <T >int binarySearch(
			List<T> array,	
			int first,
			int last,
			T L,
			Comparator<T> comparator
			)
			{
		    int len = last - first;
		    while (len > 0)
		            {
		            int half = len / 2;
		            int middle = first + half;
		
		            if ( comparator.compare(array.get(middle),L) < 0  )
		                    {
		                    first = middle + 1;
		                    len -= half + 1;
		                    }
		            else
		                    {
		                    len = half;
		                    }
		            }
		    return first;
			}
	
	}
