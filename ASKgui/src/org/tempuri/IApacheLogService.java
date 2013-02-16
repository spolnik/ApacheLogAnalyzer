/**
 * IApacheLogService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface IApacheLogService extends java.rmi.Remote {
    public java.lang.String[] getAvailableProviders() throws java.rmi.RemoteException;
    public java.lang.String[] getErrorLogsTypes(java.lang.String provider) throws java.rmi.RemoteException;
    public java.lang.String[] getAccessLogsTypes(java.lang.String provider) throws java.rmi.RemoteException;
    public byte[] getErrorLogsStatistics(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String level, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException;
    public byte[] getAccessLogsStatistics(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException;
    public byte[] getMostFrequentErrorLogs(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String type, java.lang.Integer limit, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException;
    public byte[] getMostFrequentAccessLogs(java.lang.String provider, java.util.Calendar from, java.util.Calendar to, java.lang.String type, java.lang.Integer limit, java.lang.Integer imageWidth, java.lang.Integer imageHeight) throws java.rmi.RemoteException;
}
