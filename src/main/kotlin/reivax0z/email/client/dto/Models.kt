package reivax0z.email.client.dto

import com.fasterxml.jackson.annotation.JsonInclude

// Core Email Model
data class EmailModel (val title: String, val body: String, val to: List<String>, val from: String)

// Exceptions
data class InvalidInputException (override var message: String): Exception(message)
data class InternalException (override val message: String): Exception(message)

// Responses
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InvalidInputResponse (val code: String, val message: String)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class InternalErrorResponse (val code: String, val message: String)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class AuthenticationErrorResponse (val code: String, val message: String)