package com.ws.exception;
/**
 * This method deals with service exceptions,
 * for example:The user name or password is error.
 * */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 6684364640281734796L;
    private Integer code;
    private String exceptionMessage;

    /**
     * Custom constructor
     * @param code
     * @param message
     */
    public ServiceException(String exceptionMessage) {
        super();
        this.exceptionMessage = exceptionMessage;
    }

    /**
     * Custom constructor
     * @param code
     * @param message
     */
    public ServiceException(Integer code, String exceptionMessage) {
        super();
        this.code = code;
        this.exceptionMessage = exceptionMessage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
