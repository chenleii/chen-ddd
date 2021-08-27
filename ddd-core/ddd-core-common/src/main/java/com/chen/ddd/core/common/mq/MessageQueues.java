package com.chen.ddd.core.common.mq;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/22 12:18
 */
@Named
public class MessageQueues {
    private static MessageQueuePublisher messageQueuePublisher;

    @Inject
    public void setEventPublisher(MessageQueuePublisher messageQueuePublisher) {
        if (Objects.isNull(messageQueuePublisher)) {
            throw new IllegalArgumentException("messageQueuePublisher is null.");
        }
        MessageQueues.messageQueuePublisher = messageQueuePublisher;
    }

    public static void publish(String topic, String tag, Message message) {
        messageQueuePublisher.publish(topic, tag, message);
    }
}
