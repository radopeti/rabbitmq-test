package com.radopeti

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory

@Controller("/api")
class RabbitTestController(
    val rabbitTestClient: RabbitTestClient,
    val fanoutClient: FanoutClient,
    val topicClient: TopicClient
) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @Post("/message")
    fun sendMessage(messageTemplate: MessageTemplate) {
        rabbitTestClient.sendMessage(messageTemplate.message)
    }

    @Post("/message/route")
    fun sendRoutedMessage(messageTemplate: MessageTemplate) {
        rabbitTestClient.sendRoutedMessage(messageTemplate.message)
    }

    @Post("/message/fanout")
    fun sendFanoutMessage(messageTemplate: MessageTemplate) {
        log.info("${messageTemplate.message} received")
        fanoutClient.sendMessage(messageTemplate.message)
    }

    @Post("/message/topic/{topicName}")
    fun sendMessageToTopic(
        @PathVariable topicName: String,
        messageTemplate: MessageTemplate
    ) {
        topicClient.sendMessageToTopic(topicName, messageTemplate.message)
    }
}

data class MessageTemplate(
    val message: String
)
