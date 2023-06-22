package com.Glab.LaboIntelligent.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7223891989215847979L;

	public ReviewNotFoundException(String message) {
        super(message);
    }
}
