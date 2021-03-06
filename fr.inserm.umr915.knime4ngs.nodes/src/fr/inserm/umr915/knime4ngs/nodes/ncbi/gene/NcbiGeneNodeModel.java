package fr.inserm.umr915.knime4ngs.nodes.ncbi.gene;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.xml.sax.SAXException;


import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.ncbi.AbstractNcbiEUtilsNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class NcbiGeneNodeModel extends AbstractNcbiEUtilsNodeModel
	{
    /**
     * Constructor for the node model.
     */
    protected NcbiGeneNodeModel()
    	{
       
    	}
    @Override
    protected DataTableSpec createDataSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[4];
    	cols[0]=new DataColumnSpecCreator("ncbi.gene.id", IntCell.TYPE).createSpec();
    	cols[1]=new DataColumnSpecCreator("ncbi.gene.locus", StringCell.TYPE).createSpec();
    	cols[2]=new DataColumnSpecCreator("ncbi.gene.desc", StringCell.TYPE).createSpec();
    	cols[3]=new DataColumnSpecCreator("ncbi.gene.summary", StringCell.TYPE).createSpec();
    	return new DataTableSpec(cols);
    	}
    
    @Override
    protected String getDatabase() {
    	return "gene";
    	}
    
    @Override
    protected List<DataCell[]> parseXML(InputStream in)
    		throws ExecuteException, IOException, SAXException,
    		XMLStreamException
    	{
    	List<DataCell[]> list=new ArrayList<DataCell[]>();
    	DataCell[] curr=null;
    	int depth=0;
    	XMLEventReader reader=super.xmlInputFactory.createXMLEventReader(new StreamSource(in));
		while(reader.hasNext())
			{
			XMLEvent evt=reader.nextEvent();
			if(evt.isEndElement())
				{
				--depth;
				continue;
				}
			if(!evt.isStartElement()) continue;
			++depth;
			StartElement s=evt.asStartElement();
			String localName=s.getName().getLocalPart();
			//if(localName.equals("Gene-track_geneid")) System.err.println("depth+"+depth);
			if(localName.equals("Entrezgene") && depth==2)
				{
				curr=new DataCell[]{
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell(),
						DataType.getMissingCell()
						};
				list.add(curr);
				}
			else if(localName.equals("Gene-track_geneid") && curr[0].isMissing() && depth==5)
				{
				curr[0]=new IntCell(Integer.parseInt(reader.getElementText()));
				--depth;
				}
			else if(localName.equals("Gene-ref_locus")  && curr[1].isMissing() && depth==5)
				{
				curr[1]=new StringCell(reader.getElementText());
				--depth;
				}
			else if(localName.equals("Gene-ref_desc")  && curr[2].isMissing()  && depth==5 )
				{
				curr[2]=new StringCell(reader.getElementText());
				--depth;
				}
			else if(localName.equals("Entrezgene_summary")  && curr[3].isMissing()  && depth== 3)
				{
				curr[3]=new StringCell(reader.getElementText());
				--depth;
				}
			
			}
    	
    	
    	return list;
    	}
    
	}

