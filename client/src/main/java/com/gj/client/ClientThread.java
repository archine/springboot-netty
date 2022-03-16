package com.gj.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Gjing
 **/
public class ClientThread implements Runnable{
    private final Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                int channels = selector.select();
                if (channels == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();

                    // 移除集合当前得selectionKey
                    keyIterator.remove();
                    if (selectionKey.isReadable()) {
                        this.handleRead(selector, selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 处理可读状态
    private void handleRead(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder message = new StringBuilder();
        if (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            message.append(StandardCharsets.UTF_8.decode(byteBuffer));
        }
        // 再次注册到选择器上，继续监听可读状态
        channel.register(selector, SelectionKey.OP_READ);
        System.out.println(message);
    }
}


