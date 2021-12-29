package by.vlad.liquibase_starter.exception_handling;

public class NoSuchDocumentException extends RuntimeException{
    public NoSuchDocumentException(String message) {
        super(message);
    }
}
