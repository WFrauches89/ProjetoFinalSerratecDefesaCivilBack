package br.gov.api.DefesaCivil.entities.exceptions;

public class ResourceBadRequestException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public ResourceBadRequestException(String mensagem){
        super(mensagem);
    }
}
