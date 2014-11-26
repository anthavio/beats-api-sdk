package net.anthavio.beats.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;

import net.anthavio.beats.client.model.UberError;
import net.anthavio.httl.HttlExecutionChain;
import net.anthavio.httl.HttlExecutionFilter;
import net.anthavio.httl.HttlRequest;
import net.anthavio.httl.HttlResponse;
import net.anthavio.httl.HttlSender;
import net.anthavio.httl.SenderConfigurer;
import net.anthavio.httl.api.HttlApiBuilder;
import net.anthavio.httl.auth.OAuth2;
import net.anthavio.httl.marshall.Jackson2Unmarshaller;
import net.anthavio.httl.transport.HttpUrlConfig;
import net.anthavio.httl.util.HttlUtil;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * https://developer.beatsmusic.com/
 * 
 * https://partner.api.beatsmusic.com/v1/
 * 
 * https://github.com/whitneyimura/Beats-Sdk
 * 
 * @author martin.vanek
 *
 */
public class BeatsClient implements Closeable {

	//private static final Logger logger = LoggerFactory.getLogger(UberClient.class);

	private final UberSettings settings;

	private final HttlSender sender;

	private final ObjectMapper mapper;

	private final UberApi accountsApi;

	public BeatsClient(UberSettings settings) {
		this(settings, new HttpUrlConfig(settings.getApiUrl()).sender());
	}

	public BeatsClient(UberSettings settings, SenderConfigurer config) {
		if (settings == null) {
			throw new IllegalArgumentException("Null settings");
		}
		this.settings = settings;

		if (config == null) {
			throw new IllegalArgumentException("Null config");
		}

		this.mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		SimpleModule testModule = new SimpleModule("UberModule").addDeserializer(Date.class,
				new UnixTimestampDeserializer());
		mapper.registerModule(testModule);

		Jackson2Unmarshaller jackson = new Jackson2Unmarshaller(mapper);
		config.setUnmarshaller(jackson);

		/*
		config.setParamSetter(new ConfigurableParamSetter("yyyy-MM-dd HH:mm:ss.SSS")); //2010-06-01 12:21:47.000
		
		config.addBuilderVisitor(new HttlBuilderVisitor() {

			@Override
			public void visit(HttlRequestBuilder<?> builder) {
				builder.param("application_id", settings.getApplicationId());
			}
		});
		*/
		config.addExecutionFilter(new HttlExecutionFilter() {

			@Override
			public HttlResponse filter(HttlRequest request, HttlExecutionChain chain) throws IOException {

				HttlResponse response = chain.next(request);

				if (response.getHttpStatusCode() >= 400) {
					if ("application/json".equals(response.getMediaType())) {
						UberError uberError = mapper.readValue(response.getStream(), UberError.class);
						throw new UberException(response.getHttpStatusCode(), uberError);
					} else {
						String content = HttlUtil.readAsString(response);
						throw new UberException(content);
					}
				}
				return response;
			}
		});

		this.sender = config.build();

		this.accountsApi = HttlApiBuilder.build(UberApi.class, sender);

	}

	public void close() {
		sender.close();
	}

	public UberSettings getSettings() {
		return settings;
	}

	public UberApi api() {
		return accountsApi;
	}

	/**
	 * Uber sets unix epoch time (seconds since 1970) and java Date uses milliseconds
	 * 
	 * http://wiki.fasterxml.com/JacksonHowToCustomDeserializers
	 * 
	 * @author martin.vanek
	 *
	 */
	public static class UnixTimestampDeserializer extends JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return new Date(jp.getLongValue() * 1000l);
		}

	}

	/**
	 * Just a shortcut
	 */
	public OAuth2 getOauth2() {
		return settings.getOauth2();
	}

}
