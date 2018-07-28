package reivax0z.email.client.provider

import reivax0z.email.client.dto.EmailModel

/**
 * Interface to use for delegating the actual email sending to 3rd parties (SES, Mailgun, SMTP...)
 */
interface EmailProvider {
    fun send (email: EmailModel)
}