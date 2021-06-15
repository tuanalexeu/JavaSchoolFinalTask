package com.alekseytyan.telegrambot.dto

import com.alekseytyan.telegrambot.dto.enums.LoadStatus
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.*
import java.io.Serializable

@NoArgsConstructor
@Getter @Setter
class ClientLoadDTO : Serializable {
    val clientId: String? = null
    val cityLoad: String? = null
    val cityUnload: String? = null
    val name: String? = null
    val weight = 0
    val status: LoadStatus? = null
    val token: String? = null

    override fun toString(): String {
        return "Name: $name\n" +
                "City load: $cityLoad\n" +
                "City unload: $cityUnload\n" +
                "Weight: $weight\n" +
                "Status: $status\n"
    }
}