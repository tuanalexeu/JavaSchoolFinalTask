package com.alekseytyan.logiweb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
@EnableJms
public class JmsConfig {

    private static final Logger logger = LoggerFactory.getLogger(JmsConfig.class);


    private final String login = "root";
    private final String password = "root";

    @Bean
    public Context context() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8081");
        props.put("jboss.naming.client.ejb.context", true);
        props.put(Context.SECURITY_PRINCIPAL, login);
        props.put(Context.SECURITY_CREDENTIALS, password);
        return new InitialContext(props);
    }

    @Bean
    public JMSProducer jmsProducer(@Qualifier("jmsContext") JMSContext context) {
        return context.createProducer();
    }

    @Bean
    public JMSContext jmsContext(TopicConnectionFactory connectionFactory) {
        return connectionFactory.createContext(login, password);
    }

    @Bean
    public TopicConnectionFactory connectionFactory() throws NamingException {
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context().lookup("jms/RemoteConnectionFactory");

        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter =
                new UserCredentialsConnectionFactoryAdapter();
        userCredentialsConnectionFactoryAdapter.setUsername(login);
        userCredentialsConnectionFactoryAdapter.setPassword(password);
        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);

        return userCredentialsConnectionFactoryAdapter;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestinationName("Logiweb");
        return jmsTemplate;
    }

    @Bean
    public Topic topic() throws Exception {
        TopicConnectionFactory factory = connectionFactory();

        Topic topic;

        TopicConnection topicConnection = factory.createTopicConnection(login, password);
        TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        topicConnection.start();
        topic = session.createTopic("Logiweb");

        logger.info("Connection to topic is established");

        return topic;
    }

}
