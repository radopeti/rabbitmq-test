package com.radopeti

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

@RabbitClient("micronaut")
interface RabbitTestClient {

    @Binding("analytics")
    fun sendMessage(message: String)
}
