package net.anthavio.beats.client.model;

import java.io.Serializable;
import java.util.List;

import net.anthavio.beats.client.JsonStringBuilder;

/**
 * https://developer.uber.com/v1/endpoints/#product-types
 * 
 * @author martin.vanek
 *
 */
public class UberProducts implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return JsonStringBuilder.toString(this, true);
	}

	public static class Product implements Serializable {

		private static final long serialVersionUID = 1L;

		private String product_id;

		private String description;

		private String display_name;

		private Integer capacity;

		private String image;

		public String getProduct_id() {
			return product_id;
		}

		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getDisplay_name() {
			return display_name;
		}

		public void setDisplay_name(String display_name) {
			this.display_name = display_name;
		}

		public Integer getCapacity() {
			return capacity;
		}

		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

	}

}
