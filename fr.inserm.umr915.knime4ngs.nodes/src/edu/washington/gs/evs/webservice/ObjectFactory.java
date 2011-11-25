
package edu.washington.gs.evs.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.washington.gs.evs.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetEvsData_QNAME = new QName("http://webservice.evs.gs.washington.edu/", "getEvsData");
    private final static QName _GetEvsDataResponse_QNAME = new QName("http://webservice.evs.gs.washington.edu/", "getEvsDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.washington.gs.evs.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEvsData }
     * 
     */
    public GetEvsData createGetEvsData() {
        return new GetEvsData();
    }

    /**
     * Create an instance of {@link SnpFunction }
     * 
     */
    public SnpFunction createSnpFunction() {
        return new SnpFunction();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link SiteCoverageInfo }
     * 
     */
    public SiteCoverageInfo createSiteCoverageInfo() {
        return new SiteCoverageInfo();
    }

    /**
     * Create an instance of {@link GetEvsDataResponse }
     * 
     */
    public GetEvsDataResponse createGetEvsDataResponse() {
        return new GetEvsDataResponse();
    }

    /**
     * Create an instance of {@link EvsData }
     * 
     */
    public EvsData createEvsData() {
        return new EvsData();
    }

    /**
     * Create an instance of {@link FunctionInfo }
     * 
     */
    public FunctionInfo createFunctionInfo() {
        return new FunctionInfo();
    }

    /**
     * Create an instance of {@link SnpData }
     * 
     */
    public SnpData createSnpData() {
        return new SnpData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEvsData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.evs.gs.washington.edu/", name = "getEvsData")
    public JAXBElement<GetEvsData> createGetEvsData(GetEvsData value) {
        return new JAXBElement<GetEvsData>(_GetEvsData_QNAME, GetEvsData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEvsDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.evs.gs.washington.edu/", name = "getEvsDataResponse")
    public JAXBElement<GetEvsDataResponse> createGetEvsDataResponse(GetEvsDataResponse value) {
        return new JAXBElement<GetEvsDataResponse>(_GetEvsDataResponse_QNAME, GetEvsDataResponse.class, null, value);
    }

}
