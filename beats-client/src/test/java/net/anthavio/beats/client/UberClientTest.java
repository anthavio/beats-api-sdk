package net.anthavio.beats.client;

import java.util.Properties;

import net.anthavio.beats.client.BeatsClient;
import net.anthavio.beats.client.UberSettings;
import net.anthavio.beats.client.UberToken;
import net.anthavio.beats.client.UberToken.TokenType;
import net.anthavio.beats.client.model.UberProducts;
import net.anthavio.beats.client.model.UberUserProfile;
import net.anthavio.httl.SenderConfigurer;
import net.anthavio.httl.transport.HttpClient4Config;

/**
 * 
 * @author martin.vanek
 *
 */
public class UberClientTest {

	public static void main(String[] args) {
		UberClientTest test = new UberClientTest();

		try {
			test.basic();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	//@Test
	public void basic() throws Exception {
		String callbackUrl = "http://localhost:8080/uber/oauth/callback";
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/uber-client.properties"));
		String clientId = props.getProperty("uber.app.client_id");
		String clientSecret = props.getProperty("uber.app.secret");
		String serverToken = props.getProperty("uber.app.server_token");

		UberSettings settings = new UberSettings(clientId, clientSecret, callbackUrl);
		SenderConfigurer builder = new HttpClient4Config(settings.getApiUrl()).sender();
		BeatsClient client = new BeatsClient(settings, builder);

		UberToken token = new UberToken(TokenType.SERVER, serverToken);
		UberProducts products = client.api().products(token, 37.7759792, -122.41823);
		System.out.println(products);

		settings.getOauth2().getAuthorizationUrl("profile history_lite");//history
		UberUserProfile profile = client.api().me(token.getValue());
		System.out.println(profile);

		//List<AccountStub> accounts = client.account().list("anthavio").execute().getData();
		//System.out.println(accounts);

		//Map<String, AccountInfo> info = client.account().info(504644777, 504644666);
		//System.out.println(info);
		/*
		Map<String, List<PlayersTank>> tanks = client.account().tanks(504644777);
		List<PlayersTank> tl = tanks.values().iterator().next();
		for (PlayersTank tank : tl) {
			System.out.println(tank);
		}
		*/

		//Ratings type = client.ratings().types().get(RatingType.ALL);
		//System.out.println(type);

		//Map<Long, Vehicle> vehicles = client.encyclopedia().tanks().execute().getData();
		//System.out.println(vehicles);

		//Map<Long, VehicleInfo> data = client.encyclopedia().tankinfo(4929, 3153).execute().getData();
		//System.out.println(data);

		//List<Clan> topclans = client.clan().top().execute().getData();
		//System.out.println(topclans);
		//Map<Long, Member> data = client.clan().member(504644666, 504644777, 504644669).execute().getData();
		//System.out.println(data);

		//Map<Long, PlayerRatings> player = client.ratings().player(504644777, RatingType.MONTH);
		//System.out.println(player);

		//Map<RatingType, RatingsTypesData> types = client.ratings().types().execute().getData();
		//System.out.println(types);

		//List<Ratings> top = client.ratings().top(RatingType.MONTH, RankField.damage_avg_rank).execute().getData();
		//System.out.println(top);
		client.close();

	}
}
