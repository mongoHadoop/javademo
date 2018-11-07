package com.justbon.util;



import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class JWSClient2 {

	
	// 名字空间 
															   
    public String TARGETNAMESPACE = "http://service/";
    //服务名
    public String SERVICENAME = "IntegrationWebServiceImplService";
    //端口名
    public String PORTNAME = "IntegrationWebServiceImplPort";
    //方法名
    public  final String OPER_NAME = "PendingWork";
    //参数名
    public  final String INPUT_NMAE = "username";
    
    
    public  String userPwd="";
	public static void main(String[] args) {
		String url ="http://127.0.0.1:8010/yhServices/services/IntegrationWebService?wsdl";
		String userName ="kt102";
		String appId="dddd";
		JWSClient2 client = new JWSClient2();
		String xml =client.pendingWork(userName, url, appId);
		System.out.println("调用完毕 :"+xml);
	}
	
	public String pendingWork(String userName, String url, String appId) {
		String xmlStr = "";
		//initPorperties();//初始化调用参数
		try {
			QName serviceName = new QName(TARGETNAMESPACE, SERVICENAME);
			QName portName = new QName(TARGETNAMESPACE, PORTNAME);
			Service service = Service.create(serviceName);
			service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, url);
			
			Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
			BindingProvider bp = (BindingProvider) dispatch;
			
			 Map<String, Object> rc = bp.getRequestContext();
			 rc.put(BindingProvider.SOAPACTION_USE_PROPERTY, Boolean.TRUE);
			 rc.put(BindingProvider.SOAPACTION_URI_PROPERTY, OPER_NAME);
			rc.put(BindingProvider.USERNAME_PROPERTY, userName);
			rc.put(BindingProvider.PASSWORD_PROPERTY, userPwd);

			MessageFactory factory = ((SOAPBinding) bp.getBinding()).getMessageFactory();
			SOAPMessage request = factory.createMessage();
			SOAPBody body = request.getSOAPBody();
			QName payloadName = new QName(TARGETNAMESPACE, OPER_NAME, "ns1");
			SOAPBodyElement payload = body.addBodyElement(payloadName);
			SOAPElement message = payload.addChildElement("username");
			message.addTextNode(userName);
			SOAPMessage reply = dispatch.invoke(request);
			SOAPBody soapBody = reply.getSOAPBody();
			SOAPBodyElement nextSoapBodyElement = (SOAPBodyElement) soapBody.getChildElements().next();
			SOAPElement soapElement = (SOAPElement) nextSoapBodyElement.getChildElements().next();
			xmlStr = soapElement.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return xmlStr;
		}
		return xmlStr;
	}
	
}
