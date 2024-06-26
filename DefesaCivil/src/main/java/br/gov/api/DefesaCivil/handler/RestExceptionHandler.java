package br.gov.api.DefesaCivil.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.gov.api.DefesaCivil.common.ConversorData;
import br.gov.api.DefesaCivil.entities.error.ErrorResposta;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceBadRequestException;
import br.gov.api.DefesaCivil.entities.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResposta> handlerResourceNotFoundException(ResourceNotFoundException ex){

        String data = ConversorData.converterDateParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(404, "Not Found", ex.getMessage(), data);

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResposta> handlerResourceBadRequestException(ResourceBadRequestException ex){

        String data = ConversorData.converterDateParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(400, "Bad Request", ex.getMessage(), data);

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResposta> handlerException(Exception ex){

        String data = ConversorData.converterDateParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(500, "Internal Server Error", ex.getMessage(), data);

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResposta> handlerBadCredentialsException(Exception ex){

        String data = ConversorData.converterDateParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(401, "Unauthorized", "Usuário ou senha inválidos", data);

        return new ResponseEntity<>(erro, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResposta> handlerAccessDeniedException(AccessDeniedException ex){

        String data = ConversorData.converterDateParaDataHora(new Date());

        ErrorResposta erro = new ErrorResposta(403, "Forbidden", ex.getMessage(), data);

        return new ResponseEntity<>(erro, HttpStatus.FORBIDDEN);
    }
}
