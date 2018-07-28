package reivax0z.email.client.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reivax0z.email.client.dto.EmailModel
import reivax0z.email.client.service.EmailSender
import javax.validation.constraints.NotNull

@RestController
class RestController {

    @Autowired
    lateinit var emailSender: EmailSender

    @PostMapping("/send")
    fun sendEmail(@RequestBody @NotNull email: EmailModel) {
        emailSender.send(email)
    }
}