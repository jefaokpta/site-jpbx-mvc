package br.com.jpbx.sitejpbx.bot

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication

@Component
class TelegramInit {

    @Value("\${telegram.bot.token}")
    private lateinit var telegramBotToken: String

    @Value("\${telegram.bot.my-chat-id}")
    private lateinit var myChatId: String

    @EventListener(ApplicationReadyEvent::class)
    fun run(){
        val telegramBotsLongPollingApplication = TelegramBotsLongPollingApplication()
        telegramBotsLongPollingApplication.registerBot(telegramBotToken, TelegramBot(telegramBotToken, myChatId))
    }
}