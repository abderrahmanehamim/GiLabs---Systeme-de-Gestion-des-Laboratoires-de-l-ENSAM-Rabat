package com.Glab.LaboIntelligent.exceptions;

public class PokemonNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1417013750934157297L;

	public PokemonNotFoundException(String message) {
        super(message);
    }
}
