package com.alekseytyan.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ChatApplication>(*args)
        }
    }
}