package com.springboot.soapws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.springboot.soapws.loaneligibility.Acknowledgement;
import com.springboot.soapws.loaneligibility.CustomerRequest;
import com.springboot.soapws.service.LoanEligibilityService;

@Endpoint
public class LoanEligibilityEndpoint {
	
	private static final String NAMESPACE ="http://www.springboot.com/soapws/loaneligibility";
	
	
	@Autowired
	private LoanEligibilityService loanEligibilityService;
	
	@PayloadRoot(namespace=NAMESPACE, localPart = "CustomerRequest")
	@ResponsePayload
	public Acknowledgement getLoanStatus(@RequestPayload CustomerRequest request) {
		return loanEligibilityService.checkLoanEligibility(request);
	}

}
