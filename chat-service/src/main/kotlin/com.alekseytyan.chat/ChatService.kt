package com.alekseytyan.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatService {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ChatService>(*args)
        }
    }
}