package com.alekseytyan.chat.model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ChatMessage {

    var type: MessageType? = null
    private val content: String? = null
    var sender: String? = null

    enum class MessageType {
        CHAT, JOIN, LEAVE
    }
}