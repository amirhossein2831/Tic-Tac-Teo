package com.example.game.Exception;

public class NotChooseSideException extends RuntimeException{
    public NotChooseSideException() {
        super("the side in not choose");
    }
}
