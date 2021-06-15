package com.alekseytyan.telegrambot.service

import com.alekseytyan.telegrambot.dto.ClientLoadDTO
import com.alekseytyan.telegrambot.dto.GenericResponse
import lombok.SneakyThrows
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ClientLoadService {

    @Value("\${logiweb.url}")
    private val logiwebUrl: String? = null

    private val client = OkHttpClient()

    @SneakyThrows
    fun findOrder(token: String): ClientLoadDTO? {

        val mapper = ObjectMapper()

        val request = Request.Builder()
            .url("$logiwebUrl/find-client-order?orderToken=$token")
            .build()

        val response: GenericResponse<ClientLoadDTO> = mapper.readValue(client.newCall(request).execute().body!!.string())

        return response.attachedObj
    }
}