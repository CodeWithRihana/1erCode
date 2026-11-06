package com.example.Taskboard.Exception;

/**
 * Exception levee lorsqu'une ressource viole une contrainte d'unicite (ex: email d'utilisateur deja utilise).
 * Elle sera interceptee par le GlobalExceptionHandler pour renvoyer une reponse HTTP 409 Conflict.
 */

public class DuplicateResourceException  extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
    
}
