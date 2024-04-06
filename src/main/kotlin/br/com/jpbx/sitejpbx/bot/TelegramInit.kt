package br.com.jpbx.sitejpbx.bot

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Component
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication

@Component
@EnableAsync
class TelegramInit(
    private val telegramSecurity: TelegramSecurity,
    private val telegramBot: TelegramBot
) {

    @EventListener(ApplicationReadyEvent::class)
    @Async
    fun run(){
        val telegramBotsLongPollingApplication = TelegramBotsLongPollingApplication()
        telegramBotsLongPollingApplication.registerBot(telegramSecurity.getTelegramBotToken(), telegramBot)
    }
}