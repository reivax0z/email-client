package reivax0z.email.client.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import reivax0z.email.client.dto.*
import javax.servlet.http.HttpServletRequest

@EnableWebMvc
@ControllerAdvice
class RestControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidInputException::class)
    fun handleInvalidInput(request: HttpServletRequest, exception: InvalidInputException): InvalidInputResponse {
        System.out.println("HEREEEEE")
        return InvalidInputResponse(code = "INVALID", message = exception.message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException::class)
    fun handleInternalException(request: HttpServletRequest, exception: InternalException): InternalErrorResponse {
        return InternalErrorResponse(code = "ERROR", message = exception.message)
    }
}