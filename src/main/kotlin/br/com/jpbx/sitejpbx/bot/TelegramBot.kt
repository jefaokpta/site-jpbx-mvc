package br.com.jpbx.sitejpbx.bot

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class TelegramBot(
    val token: String,
    val myChatId: String
): LongPollingSingleThreadUpdateConsumer {

    private val log = LoggerFactory.getLogger(this::class.java)
    private val telegramClient = OkHttpTelegramClient(token)

    override fun consume(update: Update) {
        if (update.hasMessage() && update.message.hasText()) {
            val message = update.message
            log.info("Message received: ${message.text} - chatID: ${message.chatId}")
            val sendMessage = SendMessage(myChatId, message.text)
            telegramClient.execute(sendMessage)
        }
    }
}