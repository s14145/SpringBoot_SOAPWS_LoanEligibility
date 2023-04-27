# Spring Boot Loan Eligibility Application using SOAP Web Service

It has been created using Contract First (WSDL -> Java Class) web service approach.
XSD (XML Schema Defintion) file for this application is "loanEligibility.xsd" which can be found in classpath resource.
The application starts at port 9090 and the endpoint is "http://localhost:9090/ws/loanEligibility.wsdl"

Input Parameter - Customer Request

Output Parameter - Acknowledgement

This application can be tested using POSTMAN API tool by hitting below endpoint and SOAP request XML file.

WSDL URI: http://localhost:9090/ws/loanEligibility.wsdl

SOAP Endpoint: http://localhost:9090/ws/loanEligibility


If you are using POSTMAN tool then in Headers section set "Content-Type = text/xml" and submit POST request:

**Request.xml**

**Note: This SOAP webservice has been implemented using WS Security with Authentication specification so SOAP request requires username and password.**

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:le="http://www.springboot.com/soapws/loaneligibility">   
          
     <soapenv:Header>
       <wsse:Security soapenv:mustUnderstand="1" xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
        <wsse:UsernameToken>
            <wsse:Username>user</wsse:Username>
            <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">password</wsse:Password>
        </wsse:UsernameToken>
       </wsse:Security>
    </soapenv:Header>
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

