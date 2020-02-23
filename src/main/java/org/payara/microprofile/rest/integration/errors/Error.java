package org.payara.microprofile.rest.integration.errors;

import io.openapitools.jackson.dataformat.hal.annotation.Resource;

@Resource
public class Error {

    private int statusCode;
    private String statusDescription;
    private String errorMessage;

    private Error(int statusCode, String statusDescription, String errorMessage) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error{" + "statusCode=" + statusCode + ", statusDescription=" + statusDescription +
                ", errorMessage=" + errorMessage + '}';
    }

    static Error of(int statusCode, String statusDescription, String errorMessage) {
        return new Error(statusCode, statusDescription, errorMessage);
    }

}
