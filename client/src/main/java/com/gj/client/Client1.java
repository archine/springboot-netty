package com.gj.client;

import java.io.IOException;

/**
 * @author Gjing
 **/
public class Client1 {
    public static void main(String[] args) throws IOException {
        new ChatClient().start("李四");
    }
}
