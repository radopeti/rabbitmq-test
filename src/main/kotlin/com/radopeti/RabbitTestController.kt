package com.radopeti

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory

@Controller("/api")
class RabbitTestController(
    val rabbitTestClient: RabbitTestClient,
    val fanoutClient: FanoutClient
) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @Post("/message")
    fun sendMessage(messageTemplate: MessageTemplate) {
        rabbitTestClient.sendMessage(messageTemplate.message)
    }

    @Post("/message/fanout")
    fun sendFanoutMessage(messageTemplate: MessageTemplate) {
        log.info("${messageTemplate.message} received")
        fanoutClient.sendMessage(messageTemplate.message)
    }
}

data class MessageTemplate(
    val message: String
)
