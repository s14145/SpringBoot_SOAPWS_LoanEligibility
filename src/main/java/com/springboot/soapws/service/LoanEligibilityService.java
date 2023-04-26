package com.springboot.soapws.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.springboot.soapws.loaneligibility.Acknowledgement;
import com.springboot.soapws.loaneligibility.CustomerRequest;

@Service
public class LoanEligibilityService {
	
	public Acknowledgement checkLoanEligibility(CustomerRequest request) {
		
		if(Objects.isNull(request)) {
			throw new RuntimeException("Please provide customer details.");
		}
		
		Acknowledgement acknowledgement = new Acknowledgement();
		List<String> mismatchCriteriaList = acknowledgement.getCriteriaMismatch();
		
		List<String> employeeModeList = Arrays.asList("GOVT","PUBLIC");
		
		
		// Checking if customer name is null or empty or blank or contains digits or special characters
		if(request.getCustomerName() == null || request.getCustomerName().trim().length() == 0 || !(request.getCustomerName().matches("[a-zA-Z]*"))) {
			mismatchCriteriaList.add("Please provide valid customer name");
		}
		// Checking if age is between 30 and 60
		if(!(request.getAge() > 30 && request.getAge() <=60)) {
			mismatchCriteriaList.add("Person age should be in between 30 to 60");
		}
		// Checking if yearly income is more than 2 hundred thousand but less than 1 billion
		if(!(request.getYearlyIncome() > 200000 && request.getYearlyIncome() <= 1000000000)) {
			mismatchCriteriaList.add("Minimum yearly incomee has to be more than Rs. 2,00,000.");
		}
		//Checking if cibil score is between 500 and 800
		if(!(request.getCibilScore() > 500 && request.getCibilScore() <= 800)) {
			mismatchCriteriaList.add("CIBIL Score has to be between 500 and 800, pleases try after 6 months");
		}
		// Checking if employee mode is null or empty or blank or contains GOVT or PUBLIC
		if(request.getEmployeeMode() == null || request.getEmployeeMode().trim().length() == 0 || !(employeeModeList.contains(request.getEmployeeMode()))){
			mismatchCriteriaList.add("Employee Mode value has to be either GOVT or PUBLIC");
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
