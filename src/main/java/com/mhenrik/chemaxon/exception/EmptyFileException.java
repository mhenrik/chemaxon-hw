package com.mhenrik.chemaxon.exception;

public class EmptyFileException extends Throwable {

    @Override
    public String getMessage() {
        return "File is empty!";
    }
}
