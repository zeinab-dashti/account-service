package com.zeinab.account.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiErrorMessage  extends RuntimeException {

    private String Url;
    private HttpStatus statusCode;
    private String error;

    public ApiErrorMessage(String Url, HttpStatus statusCode, String error) {
        super(error);
        this.Url = Url;
        this.statusCode = statusCode;
        this.error = error;
    }

}
