package com.es.practiarmar.exception;

import com.es.practiarmar.dto.error.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja tus excepciones personalizadas de negocio (CustomerException)
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDetails> handleCustomerException(CustomerException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(), // El mensaje que lanzaste en el servicio
                request.getDescription(false)
        );
        // Aquí puedes añadir lógica para mapear mensajes específicos a diferentes HTTP Status
        // Por ejemplo, si el mensaje indica un problema de negocio (ej. stock insuficiente),
        // podría ser 400. Si indica que algo ya existe, 409.
        if (ex.getMessage().contains("existe") || ex.getMessage().contains("No puedes tener clientes con el mismo nombre")) {
            return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT); // 409 Conflict
        }
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // Por defecto para otros CustomerException
    }

    // Maneja ResourceNotFoundException (cuando un recurso no existe)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // 404 Not Found
    }

    // Maneja DuplicateEntryException (cuando se intenta crear algo que ya existe)
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateEntryException(DuplicateEntryException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT); // 409 Conflict
    }

    // Maneja las excepciones de validación de @Valid en DTOs (@NotBlank, @Email, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        // Recopila todos los errores de validación de campos
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Error de validación de campos. Por favor, revise los datos ingresados.",
                request.getDescription(false),
                errors
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); // 400 Bad Request
    }

    // Manejador genérico para cualquier otra excepción no capturada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Ha ocurrido un error interno del servidor. Por favor, intente de nuevo más tarde o contacte al soporte.",
                request.getDescription(false) + " - Error: " + ex.getClass().getSimpleName()
        );
        // Log the full stack trace for debugging purposes (use a proper logger)
        ex.printStackTrace(); // En un entorno de producción, usa un logger (ej. Slf4j)
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
    }
}
