package net.anthavio.beats.client;

import net.anthavio.beats.client.model.UberPriceEstimates;
import net.anthavio.beats.client.model.UberProducts;
import net.anthavio.beats.client.model.UberTimeEstimates;
import net.anthavio.beats.client.model.UberUserActivity;
import net.anthavio.beats.client.model.UberUserProfile;
import net.anthavio.httl.HttlRequestBuilders.HttlRequestBuilder;
import net.anthavio.httl.api.HttlApi;
import net.anthavio.httl.api.HttlCall;
import net.anthavio.httl.api.HttlHeaders;
import net.anthavio.httl.api.HttlVar;
import net.anthavio.httl.api.VarSetter;

/**
 * 
 * @author martin.vanek
 *
 */
@HttlApi
public interface UberApi {

	/**
	 * https://developer.uber.com/v1/endpoints/#product-types
	 * 
	 * The Products endpoint returns information about the Uber products offered at a given location. 
	 * The response includes the display name and other details about each product, and lists the products in the proper display order.
	 * 
	 * @param token OAuth 2.0 bearer token or server_token
	 * @param latitude Latitude component of location.
	 * @param longitude Longitude component of location.
	 * @return
	 * @throws UberException
	 */
	@HttlCall("GET /v1/products")
	public UberProducts products(@HttlVar(setter = TokenSetter.class, required = true) UberToken token,
			@HttlVar(name = "latitude") double latitude,//
			@HttlVar(name = "longitude") double longitude) throws UberException;

	/**
	 * https://developer.uber.com/v1/endpoints/#price-estimates
	 * 
	 * The Price Estimates endpoint returns an estimated price range for each product offered at a given location. 
	 * The price estimate is provided as a formatted string with the full price range and the localized currency symbol.
	 * 
	 * The response also includes low and high estimates, and the ISO 4217 currency code for situations requiring currency conversion. 
	 * When surge is active for a particular product, its surge_multiplier will be greater than 1, but the price estimate already factors in this multiplier.
	 * 
	 * @param token OAuth 2.0 bearer token or server_token
	 * @param start_latitude Latitude component of start location
	 * @param start_longitude Longitude component of start location
	 * @param end_latitude Latitude component of end location
	 * @param end_longitude Longitude component of end location
	 * @return
	 * @throws UberException
	 */
	@HttlCall("GET /v1/estimates/price")
	public UberPriceEstimates price(@HttlVar(setter = TokenSetter.class, required = true) UberToken token,
			@HttlVar(name = "start_latitude") double start_latitude,//
			@HttlVar(name = "start_longitude") double start_longitude, //
			@HttlVar(name = "end_latitude") double end_latitude,//
			@HttlVar(name = "end_longitude") double end_longitude) throws UberException;

	/**
	 * https://developer.uber.com/v1/endpoints/#time-estimates
	 * 
	 * The Time Estimates endpoint returns ETAs for all products offered at a given location, 
	 * with the responses expressed as integers in seconds. 
	 * We recommend that this endpoint be called every minute to provide the most accurate, up-to-date ETAs.
	 * 
	 * @param token OAuth 2.0 bearer token or server_token
	 * @param start_latitude Latitude component
	 * @param start_longitude Longitude component
	 * @param customer_uuid Unique customer identifier to be used for experience customization
	 * @param product_id Unique identifier representing a specific product for a given latitude & longitude
	 * @return
	 * @throws UberException
	 */
	@HttlCall("GET /v1/estimates/time")
	public UberTimeEstimates time(@HttlVar(setter = TokenSetter.class, required = true) UberToken token,
			@HttlVar(name = "start_latitude") double start_latitude,//
			@HttlVar(name = "start_longitude") double start_longitude, //
			@HttlVar(name = "customer_uuid") String customer_uuid,//
			@HttlVar(name = "product_id") String product_id) throws UberException;

	/**
	 * USER PROFILE
	 * https://developer.uber.com/v1/endpoints/#user-profile
	 * 
	 * The User Profile endpoint returns information about the Uber user that has authorized with the application.
	 * 
	 * @param access_token OAuth 2.0 bearer token with the profile scope.
	 * @return
	 */
	@HttlCall("GET /v1/me")
	@HttlHeaders("Authorization: Bearer {bearer_token}")
	public UberUserProfile me(@HttlVar("bearer_token") String access_token);

	/**
	 * USER ACTIVITY (V1.1)
	 * https://developer.uber.com/v1/endpoints/#user-activity-v1-1
	 * 
	 * The User Activity endpoint returns a limited amount of data about a user's lifetime activity with Uber. 
	 * The response will include pickup and dropoff times, the distance of past requests, and information about which products were requested.
	 * 
	 * The history array in the response will have a maximum length based on the limit parameter. 
	 * The response value count may exceed limit, therefore subsequent API requests may be necessary.
	 * 
	 * @param bearer_token OAuth 2.0 bearer token with the history_lite scope.
	 * @param offset Offset the list of returned results by this amount. Default is zero.
	 * @param limit Number of items to retrieve. Default is 5, maximum is 100.
	 */
	@HttlCall("GET /v1.1/history")
	@HttlHeaders("Authorization: Bearer {bearer_token}")
	public UberUserActivity history(@HttlVar("bearer_token") String bearer_token, @HttlVar("offset") Integer offset,
			@HttlVar("limit") Integer limit);

	/**
	 * USER ACTIVITY (V1)
	 * https://developer.uber.com/v1/endpoints/#user-activity-v1
	 * 
	 * This endpoint is whitelisted and requires approval from the Uber API team to be enabled.
	 * 
	 * @param bearer_token OAuth 2.0 bearer token with the history scope.
	 * @param offset Offset the list of returned results by this amount. Default is zero.
	 * @param limit Number of items to retrieve. Default is 5, maximum is 100.
	 * @return
	 */
	@HttlCall("GET /v1/history")
	@HttlHeaders("Authorization: Bearer {bearer_token}")
	public UberUserActivity history_v1(@HttlVar("bearer_token") String bearer_token, @HttlVar("offset") Integer offset,
			@HttlVar("limit") Integer limit);

	public static class TokenSetter implements VarSetter<UberToken> {

		@Override
		public void set(UberToken value, String name, HttlRequestBuilder<?> builder) {
			builder.header("Authorization", value.getType().getHeaderPart() + " " + value.getValue());
		}
	}
}
