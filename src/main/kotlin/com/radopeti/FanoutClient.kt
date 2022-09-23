package com.radopeti

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient("fanout")
interface FanoutClient {

    @Binding("akarmi")
    fun sendMessage(message: String)
}
