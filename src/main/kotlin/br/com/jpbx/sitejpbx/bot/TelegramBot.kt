package br.com.jpbx.sitejpbx.bot

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class TelegramBot(private val telegramSecurity: TelegramSecurity): LongPollingSingleThreadUpdateConsumer {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun consume(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = update.message
            log.info("Message received: ${message.text} - chatID: ${message.chatId}")
            val sendMessage = SendMessage(message.chatId.toString(), message.text)
            telegramSecurity.getTelegramClient().execute(sendMessage)
        }
    }
}