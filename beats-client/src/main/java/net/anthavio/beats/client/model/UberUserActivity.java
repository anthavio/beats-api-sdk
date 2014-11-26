package net.anthavio.beats.client.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.anthavio.beats.client.JsonStringBuilder;

/**
 * https://developer.uber.com/v1/endpoints/#user-activity-v1-1
 * 
 * @author martin.vanek
 *
 */
public class UberUserActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer offset;

	private Integer limit;

	private Integer count;

	private List<UberUserActivityItem> history;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<UberUserActivityItem> getHistory() {
		return history;
	}

	public void setHistory(List<UberUserActivityItem> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

	public static class UberUserActivityItem implements Serializable {

		private static final long serialVersionUID = 1L;

		private String uuid;

		private Date request_time;

		private String product_id;

		private String status;

		private Float distance;

		private Date start_time;

		private Date end_time;

		private UserActivityLocation start_location; //only /v1/history

		private UserActivityLocation end_location; // only /v1/history

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public Date getRequest_time() {
			return request_time;
		}

		public void setRequest_time(Date request_time) {
			this.request_time = request_time;
		}

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Float getDistance() {
			return distance;
		}

		public void setDistance(Float distance) {
			this.distance = distance;
		}

		public Date getStart_time() {
			return start_time;
		}

		public void setStart_time(Date start_time) {
			this.start_time = start_time;
		}

		public Date getEnd_time() {
			return end_time;
		}

		public void setEnd_time(Date end_time) {
			this.end_time = end_time;
		}

		public UserActivityLocation getStart_location() {
			return start_location;
		}

		public void setStart_location(UserActivityLocation start_location) {
			this.start_location = start_location;
		}

		public UserActivityLocation getEnd_location() {
			return end_location;
		}

		public void setEnd_location(UserActivityLocation end_location) {
			this.end_location = end_location;
		}

	}

	public static class UserActivityLocation implements Serializable {

		private static final long serialVersionUID = 1L;

		private String address;

		private Float latitude;

		private Float longitude;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Float getLatitude() {
			return latitude;
		}

		public void setLatitude(Float latitude) {
			this.latitude = latitude;
		}

		public Float getLongitude() {
			return longitude;
		}

		public void setLongitude(Float longitude) {
			this.longitude = longitude;
		}

	}
}
