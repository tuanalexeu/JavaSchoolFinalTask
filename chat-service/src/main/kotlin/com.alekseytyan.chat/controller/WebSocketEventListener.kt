package com.alekseytyan.chat.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import com.alekseytyan.chat.model.ChatMessage
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class WebSocketEventListener @Autowired constructor(private val messagingTemplate: SimpMessageSendingOperations) {

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectedEvent?) {
        logger.info("Received a new web socket connection")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        val username = headerAccessor.sessionAttributes!!["username"] as String?
        if (username != null) {
            logger.info("User Disconnected : $username")
            val chatMessage = ChatMessage()
            chatMessage.type = ChatMessage.MessageType.LEAVE
            chatMessage.sender = username
            messagingTemplate.convertAndSend("/topic/public", chatMessage)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)
    }
}