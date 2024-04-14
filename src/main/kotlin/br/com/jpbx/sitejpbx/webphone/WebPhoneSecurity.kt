package br.com.jpbx.sitejpbx.webphone

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class WebPhoneSecurity {

    @Value("\${web-phone.domain}")
    private lateinit var webPhoneDomain: String

    @Value("\${web-phone.username}")
    private lateinit var webPhoneUsername: String

    @Value("\${web-phone.password}")
    private lateinit var webPhonePassword: String

    fun getWebPhone() = WebPhone(webPhoneDomain, webPhoneUsername, webPhonePassword)
}