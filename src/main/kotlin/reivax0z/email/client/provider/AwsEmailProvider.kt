package reivax0z.email.client.provider

import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import reivax0z.email.client.dto.EmailModel
import reivax0z.email.client.dto.InternalException

@Service
@Profile("AWS")
class AwsEmailProvider: EmailProvider {

    override fun send(email: EmailModel) {
        try {
            val client: AmazonSimpleEmailService = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withRegion(Regions.AP_SOUTHEAST_2).build()

            val request: SendEmailRequest = SendEmailRequest()
                    .withDestination(Destination().withToAddresses(email.to))
                    .withMessage(Message()
                            .withBody(Body().withText(Content().withCharset("UTF-8").withData(email.body)))
                            .withSubject(Content().withCharset("UTF-8").withData(email.title)))
                    .withSource(email.from)

            client.sendEmail(request)
            System.out.println("Email sent!")

        } catch (ex: Exception) {
            System.out.println("The email was not sent. Error message: " + ex.message)
            throw InternalException(message = ex.message?:"Error sending email")
        }
    }
}