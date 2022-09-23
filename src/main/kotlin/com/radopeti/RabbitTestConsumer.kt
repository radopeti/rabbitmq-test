package com.radopeti

import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener

@RabbitListener
class RabbitTestConsumer {

    @Queue("analytics")
    fun getTestMessage(message: String) {
        println(message)
    }

    @Queue("fanOne")
    fun getFanoutOneMessage(message: String) {
        println("1 $message received in fanOne")
    }

    @Queue("fanTwo")
    fun getFanoutTwoMessage(message: String) {
        println("2 $message received in fanTwo")
    }
}
