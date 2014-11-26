package net.anthavio.beats.client.model;

import java.io.Serializable;
import java.util.List;

import net.anthavio.beats.client.JsonStringBuilder;

/**
 * https://developer.uber.com/v1/endpoints/#time-estimates
 * 
 * @author martin.vanek
 *
 */
public class UberTimeEstimates implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TimeEstimate> times;

	public List<TimeEstimate> getTimes() {
		return times;
	}

	public void setTimes(List<TimeEstimate> times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

	public static class TimeEstimate implements Serializable {

		private static final long serialVersionUID = 1L;

		private String product_id;

		private String display_name;

		//ETA for the product (in seconds). Always show estimate in minutes.
		private Integer estimate;

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getDisplay_name() {
			return display_name;
		}

		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}

		public Integer getEstimate() {
			return estimate;
		}

		public void setEstimate(Integer estimate) {
			this.estimate = estimate;
		}

	}
}
