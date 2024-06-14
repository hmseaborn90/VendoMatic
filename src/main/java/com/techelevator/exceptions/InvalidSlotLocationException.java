package com.techelevator.exceptions;

import com.techelevator.util.ConsoleColors;

public class InvalidSlotLocationException extends RuntimeException{
    public InvalidSlotLocationException(String message){
        super(ConsoleColors.RED_BOLD_BRIGHT + message + ConsoleColors.RESET);
    }
}
