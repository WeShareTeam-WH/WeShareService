package com.ws.exception;

import java.util.HashMap;
import java.util.Map;
/**
 * This method deals with parameter exceptions,
 * for example:The user name or password is empty.
 * */
public class ParameterException extends Exception{

    private static final long serialVersionUID = 5472853513437065351L;
    private Map<String, String> errorMessage=new HashMap<String, String>();

    /**
     * The get method of errorMessage.
     * @return Map object.
     * */
    public Map<String, String> getErrorMessage() {
        return errorMessage;
    }

    /**
     * The set method of errorMessage.
     * @param Map object.
     * */
    public void setErrorMessage(Map<String, String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * The method is user to put the value in map.
     * @param the key of errorMessage object.
     * @param the value of errorMessage object.
     * */
    public void addValue(String filed, String message) {
        errorMessage.put(filed, message);
    }

    /**
     * The method is used to determine whether or not it is empty.
     * @return the boolean is empty.
     * */
    public Boolean isErrorMessage() {
        return !errorMessage.isEmpty();
    }
}
