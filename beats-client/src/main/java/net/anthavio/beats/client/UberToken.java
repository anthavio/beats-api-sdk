package net.anthavio.beats.client;

import java.io.Serializable;

/**
 * 
 * @author martin.vanek
 *
 */
public class UberToken implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum TokenType {

		SERVER("Token"), BEARER("Bearer");

		private final String headerPart;

		private TokenType(String headerPart) {
			this.headerPart = headerPart;
		}

		public String getHeaderPart() {
			return headerPart;
		}

	}

	private final TokenType type;

	private final String value;

	public UberToken(TokenType type, String value) {
		if (type == null) {
			throw new IllegalArgumentException("Null Token type");
		}

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Nuull or Empty Token value");
		}

		this.type = type;
		this.value = value;
	}

	public TokenType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

}
