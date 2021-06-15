package com.alekseytyan.telegrambot.dto

import com.alekseytyan.telegrambot.dto.enums.LoadStatus
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClientLoadDTO : Serializable {
    private val clientId: String? = null
    private val cityLoad: String? = null
    private val cityUnload: String? = null
    private val name: String? = null
    private val weight = 0
    private val status: LoadStatus? = null
    private val token: String? = null
}