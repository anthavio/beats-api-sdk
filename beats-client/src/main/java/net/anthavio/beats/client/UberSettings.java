package net.anthavio.beats.client;

import java.net.MalformedURLException;
import java.net.URL;

import net.anthavio.httl.HttlSender;
import net.anthavio.httl.auth.OAuth2;
import net.anthavio.httl.util.HttlUtil;

/**
 * 
 * @author martin.vanek
 *
 */
public class UberSettings {

	private final URL apiUrl;

	private final URL authUrl;

	private final String appClientId;

	private final String appSecret;

	//private final String redirectUri;

	private final OAuth2 oAuth2;

	/**
	 * Ensure that @param redirectUri is registered on Uber site
	 * https://login.uber.com/applications/{client_id}
	 * 
	 * @param appClientId - CLIENT ID
	 * @param appSecret   - SECRET
	 * @param redirectUrl - REDIRECT URL
	 */
	public UberSettings(String appClientId, String appSecret, String redirectUrl) {
		this("https://api.uber.com", "https://login.uber.com", appClientId, appSecret, redirectUrl);
	}

	/**
	 * 
	 * @param apiHost - Something instead of https://partner.api.beatsmusic.com
	 * @param authHost - https://partner.api.beatsmusic.com
	 * @param appClientId - https://login.uber.com/applications/{client_id}
	 * @param appSecret - https://login.uber.com/applications/{client_id}
	 * @param redirectUrl - https://login.uber.com/applications/{client_id}
	 */
	public UberSettings(String apiHost, String authHost, String appClientId, String appSecret, String redirectUrl) {

		this.apiUrl = checkURL(apiHost, "apiHost");
		this.authUrl = checkURL(authHost, "authHost");

		if (appClientId == null || appClientId.length() == 0) {
			throw new IllegalArgumentException("Blank app client_id");
		}
		this.appClientId = appClientId;

		if (appSecret == null || appSecret.length() == 0) {
			throw new IllegalArgumentException("Blank app secret");
		}
		this.appSecret = appSecret;

		if (redirectUrl == null || redirectUrl.length() == 0) {
			throw new IllegalArgumentException("Blank app redirectUri");
		}
		//this.redirectUri = redirectUrl;

		//https://developer.beatsmusic.com/docs/read/getting_started/Web_Server_Applications
		HttlSender sender = HttlSender.url(HttlUtil.splitUrlPath(authUrl)[0]).build();
		oAuth2 = OAuth2.Builder().setAuthorizationUrl(authUrl + "/v1/oauth2/authorize").setClientId(appClientId)
				.setClientSecret(appSecret).setTokenEndpoint(sender, authUrl + "/oauth2/token").setRedirectUri(redirectUrl)
				.build();
	}

	public URL getApiUrl() {
		return apiUrl;
	}

	public URL getAuthUrl() {
		return authUrl;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getAppClientId() {
		return appClientId;
	}

	public OAuth2 getOauth2() {
		return oAuth2;
	}

	private URL checkURL(String url, String name) {
		if (url == null || url.length() == 0) {
			throw new IllegalArgumentException("Blank " + name);
		}
		if (!url.startsWith("http")) {
			url = "https://" + url;
		}
		try {
			return new URL(url);
		} catch (MalformedURLException mux) {
			throw new IllegalArgumentException("Invalid  " + name + " " + url, mux);
		}
	}

}
