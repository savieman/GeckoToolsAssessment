package com.example.RecipeService.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException entityNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.status = HttpStatus.NOT_FOUND.name();
        errorResponse.reason = "Entity not found";

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    static class ErrorResponse {
        private String status;
        private String reason;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
