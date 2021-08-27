package com.chen.ddd.core.common.mq;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/22 12:15
 */
public interface MessageQueuePublisher {

    /**
     * 发送mq消息
     *
     * @param topic   topic
     * @param tag     tag
     * @param message 消息
     */
    void publish(String topic, String tag, Message message);
}
