package br.com.jpbx.sitejpbx.bot

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Service
class TelegramSendMessage(private val telegramSecurity: TelegramSecurity) {

    fun sendMessage(text: String) {
        val sendMessage = SendMessage(telegramSecurity.getMyChatId(), text)
        telegramSecurity.getTelegramClient().execute(sendMessage)
    }
}