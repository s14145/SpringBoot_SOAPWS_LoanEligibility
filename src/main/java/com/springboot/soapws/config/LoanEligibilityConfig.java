package com.springboot.soapws.config;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.security.auth.callback.CallbackHandler;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import com.springboot.soapws.exception.SOAPServiceFaultException;


@Configuration
@EnableWs
public class LoanEligibilityConfig extends WsConfigurerAdapter {
	
	private static final String NAMESPACE_URI = "http://www.springboot.com/soapws/loaneligibility";
	
	@Bean
    public SoapFaultMappingExceptionResolver exceptionResolver(){
        SoapFaultMappingExceptionResolver exceptionResolver = new SoapFaultMappingExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        faultDefinition.setFaultStringOrReason("default fault message");
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(SOAPServiceFaultException.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context){
		
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet,"/ws/*");
		
	}
	
	@Bean(name="loanEligibility")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
		DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
		defaultWsdl11Definition.setPortTypeName("LoanEligibilityEndpoint");
		defaultWsdl11Definition.setLocationUri("/ws");
		defaultWsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		defaultWsdl11Definition.setSchema(schema);
		return defaultWsdl11Definition;
	}
	
	@Bean
	public XsdSchema schema(){
		return new SimpleXsdSchema(new ClassPathResource("loaneligibility.xsd"));
	}
	
	// Configure XwsSecurtyInterceptor - Intercepts all SOAP web services incoming request. Add this to list of existing interceptor
	// create Callback Handler -> SimplePasswordValidationCallbackHanlder 
	// Security Policy -> securityPolicy.xml
	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor xwsSecurityInterceptor = new XwsSecurityInterceptor();
		xwsSecurityInterceptor.setCallbackHandler(callbackHandler());
		xwsSecurityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		return xwsSecurityInterceptor;
		
	}
	
	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {
		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}
	
	
	

}
