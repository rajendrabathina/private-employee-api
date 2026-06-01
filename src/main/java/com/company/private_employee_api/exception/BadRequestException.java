package com.company.private_employee_api.exception;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    public BadRequestException(String message) {
        super(message);
    }
}
