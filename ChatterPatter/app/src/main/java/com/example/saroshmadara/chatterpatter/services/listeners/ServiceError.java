package com.example.saroshmadara.chatterpatter.services.listeners;

/**
 * Created by Sarosh Madara on 02-10-2015.
 */
public class ServiceError {
    public Throwable errorObject;
    public String message;

    public ServiceError(){
        message = "";
    }

    public ServiceError(String message){
        this.message = message;
    }

    public ServiceError(String message, Throwable errorObject){
        this.message = message;
        this.errorObject = errorObject;
    }

    public Object getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(Throwable errorObject) {
        this.errorObject = errorObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
