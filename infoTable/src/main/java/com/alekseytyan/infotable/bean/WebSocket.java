package com.alekseytyan.infotable.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Method;

@Named
@ApplicationScoped
public class WebSocket {

    @Inject
    @Push(channel = "websocket")
    private PushContext push;

    public void order() {
        push.send("orders");
    }

    public void driver() {
        push.send("driver-stats");
    }

    public void truck() {
        push.send("lorry-stats");
    }

    public void interpretMsg(String text) throws Exception {
        Method updateLocation = WebSocket.class.getMethod(text);
        updateLocation.invoke(this);
    }
}
