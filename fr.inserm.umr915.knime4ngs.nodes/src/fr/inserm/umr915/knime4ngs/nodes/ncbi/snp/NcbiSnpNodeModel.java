package fr.inserm.umr915.knime4ngs.nodes.ncbi.snp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.xml.sax.SAXException;


import fr.inserm.umr915.knime4ngs.corelib.knime.ExecuteException;
import fr.inserm.umr915.knime4ngs.nodes.ncbi.AbstractNcbiEUtilsNodeModel;








/**
 * @author Pierre Lindenbaum
 */
public class NcbiSnpNodeModel extends AbstractNcbiEUtilsNodeModel
	{
    /**
     * Constructor for the node model.
     */
    protected NcbiSnpNodeModel()
    	{
       
    	}
    @Override
    protected DataTableSpec createDataSpec()
    	{
    	DataColumnSpec cols[]=new DataColumnSpec[3];
    	cols[0]=new DataColumnSpecCreator("ncbi.rs.id", IntCell.TYPE).createSpec();
    	cols[1]=new DataColumnSpecCreator("ncbi.rs.het", DoubleCell.TYPE).createSpec();
    	cols[2]=new DataColumnSpecCreator("ncbi.gene.observed", StringCell.TYPE).createSpec();
    	return new DataTableSpec(cols);
    	}
    
    @Override
    protected String getDatabase() {
    	return "snp";
    	}
    
    @Override
    protected List<DataCell[]> parseXML(InputStream in)
    		throws ExecuteException, IOException, SAXException,
    		XMLStreamException
    	{
    	List<DataCell[]> list=new ArrayList<DataCell[]>();
    	DataCell[] curr=null;
    	int depth=0;
    	Attribute att;
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
			if(localName.equals("Rs") && depth==2)
				{
				att=s.getAttributeByName(new QName("rsId"));
				
				curr=new DataCell[]{
						new IntCell(Integer.parseInt(att.getValue())),
						DataType.getMissingCell(),
						DataType.getMissingCell()
						};
				
				list.add(curr);
				}
			else if(localName.equals("Het") && curr[1].isMissing() && depth==3)
				{
				att=s.getAttributeByName(new QName("value"));
				if(att!=null)
					{
					curr[1]=new DoubleCell(Double.parseDouble(att.getValue()));
					}
				}
			else if(localName.equals("Observed")  && curr[2].isMissing()  && depth==4 )
				{
				curr[2]=new StringCell(reader.getElementText());
				--depth;
				}			
			}
    	
    	
    	return list;
    	}
    
	}

