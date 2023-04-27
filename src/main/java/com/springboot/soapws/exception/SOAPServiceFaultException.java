package com.springboot.soapws.exception;

public class SOAPServiceFaultException extends RuntimeException{
	
	private SOAPServiceFault soapServiceFault;
	
	public SOAPServiceFaultException(String message) {
		super(message);
	}
	
	public SOAPServiceFaultException(String message, Throwable cause, SOAPServiceFault soapServiceFault) {
		super(message, cause);
		this.soapServiceFault = soapServiceFault;
	}

	public SOAPServiceFault getSoapServiceFault() {
		return soapServiceFault;
	}

	public void setSoapServiceFault(SOAPServiceFault soapServiceFault) {
		this.soapServiceFault = soapServiceFault;
	}
	
	

}
