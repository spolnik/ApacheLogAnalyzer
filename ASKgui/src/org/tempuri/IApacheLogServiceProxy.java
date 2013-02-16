package org.tempuri;

public class IApacheLogServiceProxy implements org.tempuri.IApacheLogService {
  private String _endpoint = null;
  private org.tempuri.IApacheLogService iApacheLogService = null;
  
  public IApacheLogServiceProxy() {
    _initIApacheLogServiceProxy();
  }
  
  public IApacheLogServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIApacheLogServiceProxy();
  }
  
  private void _initIApacheLogServiceProxy() {
    try {
      iApacheLogService = (new org.tempuri.ApacheLogServiceLocator()).getBasicHttpBinding_IApacheLogService();
      if (iApacheLogService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iApacheLogService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iApacheLogService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iApacheLogService != null)
      ((javax.xml.rpc.Stub)iApacheLogService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IApacheLogService getIApacheLogService() {
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService;
  }
  
  public java.lang.String[] getAvailableProviders() throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getAvailableProviders();
  }
  
  public java.lang.String[] getErrorLogsTypes(java.lang.String provider) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getErrorLogsTypes(provider);
  }
  
  public java.lang.String[] getAccessLogsTypes(java.lang.String provider) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getAccessLogsTypes(provider);
  }
  
  public byte[] getErrorLogsStatistics(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String level, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getErrorLogsStatistics(provider, from, to, level, imageWidth, imageHeight);
  }
  
  public byte[] getAccessLogsStatistics(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getAccessLogsStatistics(provider, from, to, imageWidth, imageHeight);
  }
  
  public byte[] getMostFrequentErrorLogs(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String type, java.lang.Integer limit, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getMostFrequentErrorLogs(provider, from, to, type, limit, imageWidth, imageHeight);
  }
  
  public byte[] getMostFrequentAccessLogs(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String type, java.lang.Integer limit, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException{
    if (iApacheLogService == null)
      _initIApacheLogServiceProxy();
    return iApacheLogService.getMostFrequentAccessLogs(provider, from, to, type, limit, imageWidth, imageHeight);
  }
  
  
}