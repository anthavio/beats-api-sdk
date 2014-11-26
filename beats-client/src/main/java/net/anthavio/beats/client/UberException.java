package net.anthavio.beats.client;

import net.anthavio.beats.client.model.UberError;

/**
 * 
 * @author martin.vanek
 *
 */
public class UberException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UberException(String message, Throwable cause) {
		super(message, cause);
	}

	public UberException(String message) {
		super(message);
	}

	public UberException(Throwable cause) {
		super(cause);
	}

	public UberException(int httpStatus, UberError error) {
		super("HTTP: " + httpStatus + " message: " + error.toString());
	}

}
