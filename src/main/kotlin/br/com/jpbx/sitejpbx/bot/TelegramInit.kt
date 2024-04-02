package br.com.jpbx.sitejpbx.bot

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication

@Component
class TelegramInit(
    private val telegramSecurity: TelegramSecurity,
    private val telegramBot: TelegramBot
) {

    @EventListener(ApplicationReadyEvent::class)
    fun run(){
        val telegramBotsLongPollingApplication = TelegramBotsLongPollingApplication()
        telegramBotsLongPollingApplication.registerBot(telegramSecurity.getTelegramBotToken(), telegramBot)
    }
}