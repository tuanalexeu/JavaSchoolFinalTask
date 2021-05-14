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

    public void interpretMsg(String text) throws Exception {
        push.send(text);
    }
}
