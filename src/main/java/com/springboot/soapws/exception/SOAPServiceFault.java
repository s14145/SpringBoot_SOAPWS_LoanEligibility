package com.springboot.soapws.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="SOAPServiceFault", propOrder = {
		"code",
		"description"
})
public class SOAPServiceFault {
	
	private String code;
	private String description;
	
	
	public SOAPServiceFault() {
		super();
	}
	
	
	public SOAPServiceFault(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
