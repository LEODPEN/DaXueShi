package com.daxueshi.sqlwork.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author onion
 * @date 2019-06-10 -20:33
 */
@Component
@ServerEndpoint("/websocket/{email}")
public class MyWebSocket {
    private Session session;
    private static CopyOnWriteArrayList<MyWebSocket> webSockets = new CopyOnWriteArrayList<>();
    private static Map<String, Session> sessionPool = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "email") String email){
        this.session = session;
        webSockets.add(this);
        sessionPool.put(email, session);
        System.out.println("有新连接，总数为" + webSockets.size());
    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        System.out.println("连接断开，总数为" + webSockets.size());
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("出现异常");
        error.printStackTrace();
    }
    @OnMessage
    public void onMessage(String message){
        System.out.println("接受到消息" + message);
    }

    public void sendOneMessage(String email, String message){
        Session session = sessionPool.get(email);
        if(session != null){
            session.getAsyncRemote().sendText(message);
        }
    }
}
