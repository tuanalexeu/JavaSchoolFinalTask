//package com.alekseytyan.infotable.binder;
//
//import org.slf4j.LoggerFactory;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import javax.inject.Inject;
//import java.io.IOException;
//
//@Singleton
//@Startup
//public class BindingInitializer {
//  @Inject
//  private RabbitBinder binder;
//
//  @PostConstruct
//  public void initialize() {
//    try {
//      binder.configuration()
//        .addHost("localhost") // (1)
//        .setUsername("admin") // (2)
//        .setPassword("admin") // (3)
//        .setSecure(true) // (4)
//        .setConnectTimeout(10000) // (5)
//        .setConnectRetryWaitTime(10000) // (6)
//        .setRequestedConnectionHeartbeatTimeout(3); // (7)
//      binder.initialize();
//    } catch (IOException e) {
//      LoggerFactory.getLogger(getClass()).error("Unable to initialize", e);
//    }
//  }
//}