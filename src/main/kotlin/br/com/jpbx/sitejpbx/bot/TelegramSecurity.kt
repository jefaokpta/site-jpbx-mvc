package br.com.jpbx.sitejpbx.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient

@Component
class TelegramSecurity {

    @Value("\${telegram.bot.token}")
    private lateinit var telegramBotToken: String

    @Value("\${telegram.bot.my-chat-id}")
    private lateinit var myChatId: String

    private var telegramClient: OkHttpTelegramClient? = null

    fun getTelegramClient(): OkHttpTelegramClient {
        return telegramClient ?: OkHttpTelegramClient(telegramBotToken)
    }

    fun getMyChatId(): String {
        return myChatId
    }

    fun getTelegramBotToken(): String {
        return telegramBotToken
    }
}