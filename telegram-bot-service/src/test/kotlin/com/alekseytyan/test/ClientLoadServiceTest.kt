package com.alekseytyan.test

import org.junit.runner.RunWith
import org.mockito.InjectMocks
import com.alekseytyan.telegrambot.service.ClientLoadService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClientLoadServiceTest {

    @InjectMocks
    var clientLoadService: ClientLoadService? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun findOrderTest() {
        val token = clientLoadService!!.findOrder("invalid-token")
        Assert.assertNull(token)
    }
}