package com.chen.ddd.infrastructure.mq;

import com.chen.ddd.core.common.mq.Message;
import com.chen.ddd.core.common.mq.MessageQueuePublisher;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

/**
 * @author cl
 * @version 1.0
 * @since 2021/7/22 12:22
 */
@Slf4j
@Named
public class DefaultMessageQueuePublisher implements MessageQueuePublisher {


    @Override
    public void publish(String topic, String tag, Message message) {

    }
}
