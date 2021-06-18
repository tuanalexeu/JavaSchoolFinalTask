package com.alekseytyan.test

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import com.alekseytyan.telegrambot.service.LogiwebBotService
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.telegram.telegrambots.meta.api.objects.Update

@RunWith(MockitoJUnitRunner::class)
class BotServiceTest {

    @InjectMocks
    var service: LogiwebBotService? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun usernameTest() {
        Assertions.assertDoesNotThrow { service!!.botUsername }
    }

    @Test
    fun tokenTest() {
        Assertions.assertDoesNotThrow { service!!.botToken }
    }

    @Test
    fun onUpdateReceivedTest() {
        Assertions.assertDoesNotThrow { service!!.onUpdateReceived(Update()) }
    }
}