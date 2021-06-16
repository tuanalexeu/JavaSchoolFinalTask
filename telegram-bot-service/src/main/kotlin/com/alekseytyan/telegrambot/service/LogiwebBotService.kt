package com.alekseytyan.telegrambot.service

import com.vdurmont.emoji.EmojiParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Service
class LogiwebBotService : TelegramLongPollingBot() {

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Value("\${telegram.token}")
    private val token: String = ""

    @Value("\${logiweb.url}")
    private val logiwebUrl: String = ""

    @Value("\${tsd.url}")
    private val tsdUrl: String = ""

    private val greeting: String = EmojiParser.parseToUnicode(
        ":wave: Hi, my name is Logiweb.\n" +
                ":smiley: I will help you to find your order.\n" +
                ":bee: If you want to find one,\n please, type \'/check <YOUR-ORDER-TOKEN>\'")

    @Autowired
    private val clientService: ClientLoadService? = null

    override fun getBotUsername(): String = botName

    override fun getBotToken(): String = token

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) {
            val message = update.message
            val chatId = message.chatId

            if (message.hasText()) {
                when {
                    message.text.startsWith("/check") -> {
                        try {
                            val order = clientService?.findOrder(message.text.split(" ")[1])
                            sendNotification(chatId, order?.toString() ?: "Order not found")
                        } catch (e: Exception) {
                            sendNotification(chatId, "Token needed")
                        }
                    }
                    message.text.startsWith("Go to website") -> {
                        execute(SendMessage()
                            .setChatId(message.chatId)
                            .setText(EmojiParser.parseToUnicode("Go to our website :arrow_down:"))
                            .setReplyMarkup(InlineKeyboardMarkup().apply {
                                keyboard = listOf(
                                    listOf(InlineKeyboardButton("logiweb.site").apply {
                                        url = logiwebUrl
                                    })
                                )
                            })
                        )
                    }
                    message.text.startsWith("About us") -> {
                        execute(SendMessage()
                            .setChatId(message.chatId)
                            .setText(EmojiParser.parseToUnicode("Read about us :arrow_down:"))
                            .setReplyMarkup(InlineKeyboardMarkup().apply {
                                keyboard = listOf(
                                    listOf(InlineKeyboardButton("tsd.logiweb.site").apply {
                                        url = tsdUrl
                                    })
                                )
                            })
                        )
                    }
                    else -> {
                        when (message.text) {
                            "/start" -> sendNotification(chatId,greeting)
                            "Check order status" -> sendNotification(chatId,"Please, enter \'/check <YOUR-TOKEN>\'")
                            "Make new order" -> sendNotification(chatId,"Adding new order logic")
                            else -> sendNotification(chatId,"Invalid command")
                        }
                    }
                }
            } else {
                sendNotification(chatId,"Sorry, I can't understand you!")
            }
        }
    }

    private fun sendNotification(chatId: Long, responseText: String) {
        val responseMessage = SendMessage(chatId, responseText)
        responseMessage.setParseMode("Markdown")
        responseMessage.replyMarkup = getReplyMarkup(
                listOf(
                        listOf("Check order status"),
                        listOf("Go to website", "About us")
                )
        )
        execute(responseMessage)
    }

    private fun getReplyMarkup(allButtons: List<List<String>>): ReplyKeyboardMarkup {
        val markup = ReplyKeyboardMarkup()
        markup.keyboard = allButtons.map { rowButtons ->
            val row = KeyboardRow()
            rowButtons.forEach { rowButton -> row.add(rowButton) }
            row
        }
        return markup
    }
}