package com.businessServer.objects;

/**
 * CustomResponse class represents a custom response object that contains a
 * message, status code, and timestamp.
 */
public class CustomResponse {

	private String message;
	private int statusCode;
	private String timestamp;

	/**
	 * Default constructor.
	 */
	public CustomResponse() {
	}

	/**
	 * Constructor for creating a CustomResponse with specified properties.
	 *
	 * @param message    the message of the response
	 * @param statusCode the status code of the response
	 * @param timestamp  the timestamp of the response
	 */
	public CustomResponse(String message, int statusCode, String timestamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}

	/**
	 * Retrieves the message of the response.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message of the response.
	 *
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Retrieves the status code of the response.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code of the response.
	 *
	 * @param statusCode the status code to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Retrieves the timestamp of the response.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp of the response.
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
