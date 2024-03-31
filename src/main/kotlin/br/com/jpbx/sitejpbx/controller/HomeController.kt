package br.com.jpbx.sitejpbx.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/")
class HomeController {

    @GetMapping
    fun home() = "home"

}

@RestController
@RequestMapping("/api")
class MessageController {

    @PostMapping("/new-message")
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

        if (host != "localhost:8080") {
            throw RuntimeException("Host invalido!")
        }

        return jacksonObjectMapper().writeValueAsString(mapOf("response" to "Message received!"))
    }
}