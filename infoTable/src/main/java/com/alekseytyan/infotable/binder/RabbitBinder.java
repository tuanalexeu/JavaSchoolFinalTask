//package com.alekseytyan.infotable.binder;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import net.reini.rabbitmq.cdi.EventBinder;
//import net.reini.rabbitmq.cdi.ExchangeDeclaration;
//import net.reini.rabbitmq.cdi.QueueDeclaration;
//
//import javax.enterprise.context.Dependent;
//
//@Dependent
//public class RabbitBinder extends EventBinder {
//  @Override
//  protected void bindEvents() {
//
//    ExchangeDeclaration testExchangeOne = declarerFactory()
//    .createExchangeDeclaration("test.exchange.one")
//      .withAutoDelete(false)
//      .withDurable(false); // (1)
//
//    QueueDeclaration testQueue = declarerFactory()
//    .createQueueDeclaration("Logiweb")
//      .withAutoDelete(false)
//      .withDurable(false)
//      .withExclusiveAccess(false); // (2)
//
//    bind(EventOne.class)
//      .toExchange("test.exchange.one")
//      .withDeclaration(testExchangeOne); // (3)
//
//    bind(EventOne.class)
//      .toExchange("test.exchange.two")
//      .withRoutingKey("test.key");
//
//    bind(EventTwo.class)
//      .toQueue("test.queue");
//
//    bind(EventTwo.class)
//      .toQueue("test.queue")
//      .withDeclaration(testQueue)
//      .autoAck(); // (5)
//  }
//}