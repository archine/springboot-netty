package com.gj.client;

import java.io.IOException;

/**
 * @author Gjing
 **/
public class Client2 {
    public static void main(String[] args) throws IOException {
        new ChatClient().start("张三");
    }
}
