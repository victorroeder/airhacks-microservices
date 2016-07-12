package com.airhacks.numbers.boundary;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ServerEndpoint("numbers")
public class NumberEndpoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(String msg) {
        try {
            session.getBasicRemote().sendText("pong: " + msg);
        } catch (IOException ex) {
            Logger.getLogger(NumberEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
