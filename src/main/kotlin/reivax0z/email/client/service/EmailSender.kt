package reivax0z.email.client.service

import org.apache.commons.validator.routines.EmailValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reivax0z.email.client.dto.EmailModel
import reivax0z.email.client.dto.InvalidInputException
import reivax0z.email.client.provider.EmailProvider

@Service
class EmailSender {
    @Autowired
    lateinit var emailProvider: EmailProvider

    companion object {
        var validator = EmailValidator.getInstance()
    }

    fun send(email: EmailModel) {
        validateInput(email)

        // TODO: Delegate to SES API
        emailProvider.send(email)
    }

    private fun validateInput(email: EmailModel) {
        if (email.body == null) {
            throw InvalidInputException(message = "Body cannot be null")
        }
        if (email.title == null) {
            throw InvalidInputException(message = "Title cannot be null")
        }
        if (!validateEmail(email.from)) {
            throw InvalidInputException(message = "Invalid from address")
        }
        email.to.forEach { it ->
            if (!validateEmail(it)) {
                throw InvalidInputException(message = "Invalid to address")
            }
        }
    }

    private fun validateEmail(address: String): Boolean {
        return EmailSender.validator.isValid(address)
    }
}