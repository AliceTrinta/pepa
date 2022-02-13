package com.example.pepa.crossCutting.exceptions

import com.example.pepa.webApi.controllers.clinicalBackground.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ClinicalBackgroundExceptionHandler {
    @ExceptionHandler
    fun handleGenericException(exc: Exception): ResponseEntity<ErrorResponse> {
        val clinicalBackgroundErrorResponse = ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), exc.message
        )

        return ResponseEntity(clinicalBackgroundErrorResponse, HttpStatus.BAD_REQUEST)
    }
}