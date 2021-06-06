package com.alekseytyan.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatService

fun main(args: Array<String>) {
    runApplication<ChatService>(*args)
}