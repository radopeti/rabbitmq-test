package com.radopeti

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient("topic")
interface TopicClient {

    fun sendMessageToTopic(@Binding topicName: String, message: String)
}
