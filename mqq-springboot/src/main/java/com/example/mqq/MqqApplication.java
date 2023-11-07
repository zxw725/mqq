package com.example.mqq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
@EnableRedisHttpSession
@SpringBootApplication
public class MqqApplication {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
    public static void main(String[] args) {
        SpringApplication.run(MqqApplication.class, args);
    }

}
