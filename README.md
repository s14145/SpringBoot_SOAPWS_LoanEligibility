# Spring Boot Loan Eligibility Application using SOAP Web Service

It has been created using Contract First (WSDL -> Java Class) web service approach.
XSD (XML Schema Defintion) file for this application is "loanEligibility.xsd" which can be found in classpath resource.
The application starts at port 8080 and the endpoint is "http://localhost:8080/ws/loanEligibility.wsdl"

Input Parameter - Customer Request

Output Parameter - Acknowledgement

This application can be tested using POSTMAN API tool by hitting below endpoint and SOAP request XML file.

Endpoint: http://localhost:8080/ws/loanEligibility

**Request.xml**


<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:le="http://www.springboot.com/soapws/loaneligibility">   
          
     <soapenv:Header/>
       <soapenv:Body>
        <le:CustomerRequest>
            <le:customerName>John</le:customerName>
            <le:age>35</le:age>
            <le:yearlyIncome>300000</le:yearlyIncome>
            <le:requestAmount>500000</le:requestAmount>
            <le:cibilScore>600</le:cibilScore>
            <le:employeeMode>GOVT</le:employeeMode>
        </le:CustomerRequest>
    </soapenv:Body> 
    
</soapenv:Envelope>

