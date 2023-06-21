package com.example.game.Exception;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException() {
        super("the field is empty");
    }
}
