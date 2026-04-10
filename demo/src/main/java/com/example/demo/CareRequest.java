package com.example.demo;

public class CareRequest
{
    private String patientName;
    private String callbackNumber;
    private String requestType;

    public CareRequest()
    {
    }

    public CareRequest(String patientName, String callbackNumber, String requestType)
    {
        this.patientName = patientName;
        this.callbackNumber = callbackNumber;
        this.requestType = requestType;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getCallbackNumber()
    {
        return callbackNumber;
    }

    public void setCallbackNumber(String callbackNumber)
    {
        this.callbackNumber = callbackNumber;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }
}