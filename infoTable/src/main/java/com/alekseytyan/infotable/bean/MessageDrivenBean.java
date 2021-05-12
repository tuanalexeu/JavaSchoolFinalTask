package com.alekseytyan.infotable.bean;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "Listener", activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(
                propertyName = "destinationLookup",
                propertyValue = "java:/jms/topic/Logiweb"),
        @ActivationConfigProperty(
                propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class MessageDrivenBean implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(MessageDrivenBean.class);

    private final WebSocket webSocket;

    public MessageDrivenBean() {
        webSocket = new WebSocket();
    }

    @SneakyThrows
    @Override
    public void onMessage(Message message) {

        logger.info("Message received: " + message);

        TextMessage textMessage = (TextMessage) message;
        webSocket.interpretMsg(textMessage.getText());
    }

}