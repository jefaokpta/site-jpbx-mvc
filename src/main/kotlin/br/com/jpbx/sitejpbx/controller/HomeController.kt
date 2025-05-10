package br.com.jpbx.sitejpbx.controller

import br.com.jpbx.sitejpbx.bot.TelegramSendMessage
import br.com.jpbx.sitejpbx.webphone.WebPhoneSecurity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/")
class HomeController(private val webPhoneSecurity: WebPhoneSecurity) {

    @GetMapping
    fun home(): ModelAndView {
        return ModelAndView("home").apply {
            addObject("webPhone", webPhoneSecurity.getWebPhone())
        }
    }

}

@RestController
@RequestMapping("/api")
class MessageController(private val telegramSendMessage: TelegramSendMessage) {

    @PostMapping("/new-message")
    @CrossOrigin
    fun newMessage(
        @RequestParam("name") name: String,
        @RequestParam("email") email: String,
        @RequestParam("message") message: String,
        @RequestHeader(HttpHeaders.HOST) host: String
    ): String {
        println("Name: $name")
        println("Email: $email")
        println("Message: $message")
        println("Host: $host")

        telegramSendMessage.sendMessage("Name: $name\nEmail: $email\nMessage: $message")

        return jacksonObjectMapper().writeValueAsString(mapOf("status" to "success"))
    }
}