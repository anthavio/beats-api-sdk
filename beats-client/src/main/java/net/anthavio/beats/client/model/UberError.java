package net.anthavio.beats.client.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * https://developer.uber.com/v1/api-reference/#request-response
 * 
 * @author martin.vanek
 *
 */
public class UberError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private String code;

	private List<Map<String, String[]>> fields;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Map<String, String[]>> getFields() {
		return fields;
	}

	public void setFields(List<Map<String, String[]>> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "UberError [code=" + code + ", message=" + message + ", fields=" + fields + "]";
	}

}
