package com.alekseytyan.telegrambot.dto

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
class GenericResponse<T> {
    val attachedObj: T? = null

    override fun toString(): String {
        return attachedObj.toString()
    }
}