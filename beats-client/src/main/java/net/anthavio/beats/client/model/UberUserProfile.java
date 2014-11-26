package net.anthavio.beats.client.model;

import java.io.Serializable;

import net.anthavio.beats.client.JsonStringBuilder;

/**
 * https://developer.uber.com/v1/endpoints/#user-profile
 * 
 * @author martin.vanek
 *
 */
public class UberUserProfile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String first_name;

	private String last_name;

	private String email;

	private String picture;

	private String promo_code;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPromo_code() {
		return promo_code;
	}

	public void setPromo_code(String promo_code) {
		this.promo_code = promo_code;
	}

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

}
