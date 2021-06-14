package com.alekseytyan.telegrambot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class TelegramBotApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ApiContextInitializer.init()
            runApplication<TelegramBotApplication>(*args)
        }
    }
}