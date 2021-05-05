package com.alekseytyan;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
		  name = "JMSSampleMDB",
		  activationConfig = {
		    @ActivationConfigProperty(propertyName  = "destinationType",
		                              propertyValue = "javax.jms.Queue"),
		    @ActivationConfigProperty(propertyName  = "destination", 
		                              propertyValue = "logiweb.update")
})
public class JMSSampleMDB implements MessageListener {
	
	 @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	  public void onMessage(Message message) {
	 }
}