package com.springboot.soapws.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import com.springboot.soapws.loaneligibility.Acknowledgement;
import com.springboot.soapws.loaneligibility.CustomerRequest;

@Service
public class LoanEligibilityService {
	
	public Acknowledgement checkLoanEligibility(CustomerRequest request) {
		
		if(request == null || StringUtils.isEmpty(request)) {
			throw new RuntimeException("Please provide customer details.");
		}
		
		Acknowledgement acknowledgement = new Acknowledgement();
		List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();
		
		if(!(request.getAge() > 30 && request.getAge() <=60)) {
			mismatchCriteriaList.add("Person age should be in between 30 to 60");
		}else if(!(request.getYearlyIncome() > 200000)) {
			mismatchCriteriaList.add("Minimum yearly incomee has to be more than Rs. 2,00,000");
		}else if(!(request.getCibilScore() > 500)) {
			mismatchCriteriaList.add("Low CIBIL Score, pleases try after 6 months");
		}
		
		if(mismatchCriteriaList.size() > 0) {
			acknowledgement.setApprovedAmount(0);
			acknowledgement.setIsEligible(false);
		}else {
			acknowledgement.setApprovedAmount(request.getRequestAmount());
			acknowledgement.setIsEligible(true);
			mismatchCriteriaList.clear();
		}
		
		return acknowledgement;
	}

}
