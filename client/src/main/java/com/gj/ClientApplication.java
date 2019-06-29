package com.gj;

import com.gj.client.NettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
        //启动netty客户端
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }
}
