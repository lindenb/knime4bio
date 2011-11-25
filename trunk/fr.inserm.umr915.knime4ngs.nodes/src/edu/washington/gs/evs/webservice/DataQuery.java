
package edu.washington.gs.evs.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "DataQuery", targetNamespace = "http://webservice.evs.gs.washington.edu/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DataQuery {


    /**
     * 
     * @param arg0
     * @return
     *     returns edu.washington.gs.evs.webservice.EvsData
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getEvsData", targetNamespace = "http://webservice.evs.gs.washington.edu/", className = "edu.washington.gs.evs.webservice.GetEvsData")
    @ResponseWrapper(localName = "getEvsDataResponse", targetNamespace = "http://webservice.evs.gs.washington.edu/", className = "edu.washington.gs.evs.webservice.GetEvsDataResponse")
    public EvsData getEvsData(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
