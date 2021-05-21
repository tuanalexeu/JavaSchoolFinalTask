package com.alekseytyan.infotable.bean;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

@WebListener
public class Listener implements ServletContextListener {

   private static final Logger logger = LoggerFactory.getLogger(Listener.class);

   @Inject
   private PushBean pushBean;

   @SneakyThrows
   @Override
   public void contextInitialized(ServletContextEvent event) {
      ExecutorService service = Executors.newCachedThreadPool();

      logger.info("Startup");

      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");
      Connection connection = factory.newConnection();
      Channel channel = connection.createChannel();

      channel.queueDeclare("Logiweb", false, false, false, null);
      logger.info(" [*] Waiting for messages. ");


      service.submit(() -> {

         while (true) {
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
               String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

               logger.info(" [x] Received '" + message + "'");
               logger.info("Push bean: " + pushBean.toString());

               pushBean.sendMessage(message);

               logger.info("Message [" + message + "] has been pushed to JSF page");


            };

            try {
               channel.basicConsume("Logiweb", true, deliverCallback, consumerTag -> { });
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      });
   }
}