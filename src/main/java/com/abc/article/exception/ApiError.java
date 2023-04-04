package com.abc.article.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private Date timeOccurred;

    private String message;
    private List<String> errors;

    public ApiError(){
        this.timeOccurred = new Date();
        this.errors = new ArrayList<>();
    }

    public ApiError(HttpStatus status, String message) {
        super();
        this.status = status;
        this.timeOccurred = new Date();
        this.message = message;
        this.errors = new ArrayList<>();
    }
}
