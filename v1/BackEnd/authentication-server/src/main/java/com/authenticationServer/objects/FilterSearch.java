package com.authenticationServer.objects;

/**
 * FilterSearch class represents a filter search object used for searching users.
 */
public class FilterSearch {

	private String type;
	private String data;
	
	/**
	 * Default constructor.
	 */
	public FilterSearch(){}

	/**
	 * Constructor for creating a FilterSearch with specified type and data.
	 *
	 * @param type the type of filter search
	 * @param data the data used for filtering
	 */
	public FilterSearch(String type, String data) {
		super();
		this.type = type;
		this.data = data;
	}

	/**
	 * Retrieves the type of the filter search.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the filter search.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Retrieves the data used for filtering.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data used for filtering.
	 *
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}
}
