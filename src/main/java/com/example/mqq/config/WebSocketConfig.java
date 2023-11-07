package com.example.mqq.config;
import com.example.mqq.handle.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     * registerWebSocketHandlers 是一个函数或方法，通常用于在 Web 应用程序中注册 WebSocket 处理程序。
     * WebSocket 是一种基于 TCP 的协议，可以实现客户端和服务器之间的双向通信，可以用于实时应用程序，如聊天应用、游戏、实时更新等。在 Java Web 应用程序中，可以使用 Spring 框架提供的 WebSocket 支持来处理 WebSocket 连接。
     * registerWebSocketHandlers 方法是 Spring WebSocket 的一个 API，它允许开发人员在应用程序中注册 WebSocket 处理程序，并将其映射到特定的 URI。在调用 registerWebSocketHandlers 方法时，需要传递一个 WebSocketHandler 实例和一个 URI 路径作为参数。当客户端请求与该 URI 路径对应的 WebSocket 连接时，Spring 将调用相应的 WebSocket 处理程序来处理连接。
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //参数1：注册我们自定义的MyWebSocketHandler类
        //参数2：路径【UniApp中建立连接的路径】如：我的ip是192.168.1.8:8099则UniApp需要输入的url是ws://192.168.1.8:8099/websocket
        //参数3：setAllowedOrigins("*")设置允许全部来源【在WebSocket中，浏览器会发送一个带有Origin头部的HTTP请求来请求建立WebSocket连接。服务器可以使用setAllowedOrigins方法来设置允许的来源，即允许建立WebSocket连接的域名或IP地址。这样，服务器就可以限制建立WebSocket连接的客户端，避免来自不信任的域名或IP地址的WebSocket连接。】
        registry.addHandler(new ChatWebSocketHandler(), "/chat").setAllowedOrigins("*");

    }


}
