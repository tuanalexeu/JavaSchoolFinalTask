package com.alekseytyan.logiweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
@PropertySource("classpath:jms.properties")
public class JmsConfig {
//
//    private static final Logger logger = LoggerFactory.getLogger(JmsConfig.class);
//
//    private final String SEC_CRED_LOGIN = "admin";
//    private final String SEC_CRED_PASS = "admin";
//
//    @Bean
//    public Context context() throws NamingException {
//        Properties props = new Properties();
//        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.artemis.jndi.ActiveMQInitialContextFactory");
//        props.put(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
//        props.put("jboss.naming.client.ejb.context", true);
//        props.put(Context.SECURITY_PRINCIPAL, SEC_CRED_LOGIN);
//        props.put(Context.SECURITY_CREDENTIALS, SEC_CRED_PASS);
//        return new InitialContext(props);
//    }
//
//    @Bean
//    public JMSProducer jmsProducer(@Qualifier("jmsContext") JMSContext context) {
//        return context.createProducer();
//    }
//
//    @Bean
//    public JMSContext jmsContext(TopicConnectionFactory connectionFactory) {
//        return connectionFactory.createContext(SEC_CRED_LOGIN, SEC_CRED_PASS);
//    }
//
//    @Bean
//    public TopicConnectionFactory connectionFactory() throws NamingException {
//        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context().lookup("java:/RemoteJmsXA");
//
//        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter =
//                new UserCredentialsConnectionFactoryAdapter();
//        userCredentialsConnectionFactoryAdapter.setUsername(SEC_CRED_LOGIN);
//        userCredentialsConnectionFactoryAdapter.setPassword(SEC_CRED_PASS);
//        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
//
//        return userCredentialsConnectionFactoryAdapter;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate() throws NamingException {
//        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
//        jmsTemplate.setDefaultDestinationName("Logiweb");
//        return jmsTemplate;
//    }
//
//    @Bean
//    public Topic topic() throws Exception {
//        TopicConnectionFactory factory = connectionFactory();
//
//        Topic topic;
//
//        try (TopicConnection topicConnection = factory.createTopicConnection(SEC_CRED_LOGIN, SEC_CRED_PASS);
//             TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE)) {
//            topicConnection.start();
//            topic = session.createTopic("Logiweb");
//        }
//
//        logger.info("Connection's been established");
//
//
//        return topic;
//    }

}
