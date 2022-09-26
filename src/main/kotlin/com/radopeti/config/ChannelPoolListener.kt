package com.radopeti.config

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import io.micronaut.rabbitmq.connect.ChannelInitializer
import jakarta.inject.Singleton
import java.io.IOException

@Singleton
class ChannelPoolListener : ChannelInitializer() {

    @Throws(IOException::class)
    override fun initialize(channel: Channel, name: String) {
        //direct exchange with two queues binded
        channel.exchangeDeclare("micronaut", BuiltinExchangeType.DIRECT, true)
        channel.queueDeclare("analytics", true, false, false, null)
        channel.queueBind("analytics", "micronaut", "analytics")

        channel.queueDeclare("routed", true, false, false, null)
        channel.queueBind("routed", "micronaut", "route66")

        //fanout exchange, routing key is ignore (it could be anything)
        channel.exchangeDeclare("fanout", BuiltinExchangeType.FANOUT)
        channel.queueDeclare("fanOne", true, false, false, null)
        channel.queueBind("fanOne", "fanout", "routing")

        channel.queueDeclare("fanTwo", true, false, false, null)
        channel.queueBind("fanTwo", "fanout", "key")
    }
}
