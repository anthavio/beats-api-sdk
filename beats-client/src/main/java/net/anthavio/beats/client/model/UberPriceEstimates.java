package net.anthavio.beats.client.model;

import java.io.Serializable;
import java.util.List;

import net.anthavio.beats.client.JsonStringBuilder;

/**
 * 
 * https://developer.uber.com/v1/endpoints/#price-estimates
 * 
 * @author martin.vanek
 *
 */
public class UberPriceEstimates implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PriceEstimate> prices;

	public List<PriceEstimate> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceEstimate> prices) {
		this.prices = prices;
	}

	public static class PriceEstimate implements Serializable {

		private static final long serialVersionUID = 1L;

		private String product_id;

		private String currency_code; // ISO 4217 currency code

		private String display_name;

		private String estimate;

		private Integer low_estimate;

		private Integer high_estimate;

		private Float surge_multiplier;

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getCurrency_code() {
			return currency_code;
		}

		public void setCurrency_code(String currency_code) {
			this.currency_code = currency_code;
		}

		public String getDisplay_name() {
			return display_name;
		}

		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}

		public String getEstimate() {
			return estimate;
		}

		public void setEstimate(String estimate) {
			this.estimate = estimate;
		}

		public Integer getLow_estimate() {
			return low_estimate;
		}

		public void setLow_estimate(Integer low_estimate) {
			this.low_estimate = low_estimate;
		}

		public Integer getHigh_estimate() {
			return high_estimate;
		}

		public void setHigh_estimate(Integer high_estimate) {
			this.high_estimate = high_estimate;
		}

		public Float getSurge_multiplier() {
			return surge_multiplier;
		}

		public void setSurge_multiplier(Float surge_multiplier) {
			this.surge_multiplier = surge_multiplier;
		}

	}

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}
}
