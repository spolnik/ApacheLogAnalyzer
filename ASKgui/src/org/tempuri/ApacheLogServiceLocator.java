/**
 * ApacheLogServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ApacheLogServiceLocator extends org.apache.axis.client.Service implements org.tempuri.ApacheLogService {

    public ApacheLogServiceLocator() {
    }


    public ApacheLogServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ApacheLogServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IApacheLogService
    private java.lang.String BasicHttpBinding_IApacheLogService_address = "http://localhost:8732/ApacheLogMonitorService/";

    public java.lang.String getBasicHttpBinding_IApacheLogServiceAddress() {
        return BasicHttpBinding_IApacheLogService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IApacheLogServiceWSDDServiceName = "BasicHttpBinding_IApacheLogService";

    public java.lang.String getBasicHttpBinding_IApacheLogServiceWSDDServiceName() {
        return BasicHttpBinding_IApacheLogServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IApacheLogServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IApacheLogServiceWSDDServiceName = name;
    }

    public org.tempuri.IApacheLogService getBasicHttpBinding_IApacheLogService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IApacheLogService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IApacheLogService(endpoint);
    }

    public org.tempuri.IApacheLogService getBasicHttpBinding_IApacheLogService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_IApacheLogServiceStub _stub = new org.tempuri.BasicHttpBinding_IApacheLogServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IApacheLogServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IApacheLogServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IApacheLogService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IApacheLogService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_IApacheLogServiceStub _stub = new org.tempuri.BasicHttpBinding_IApacheLogServiceStub(new java.net.URL(BasicHttpBinding_IApacheLogService_address), this);
                _stub.setPortName(getBasicHttpBinding_IApacheLogServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IApacheLogService".equals(inputPortName)) {
            return getBasicHttpBinding_IApacheLogService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ApacheLogService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IApacheLogService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IApacheLogService".equals(portName)) {
            setBasicHttpBinding_IApacheLogServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
