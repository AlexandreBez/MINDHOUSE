package com.studentsystem.customResponse;

public class CustomResponse {

	private String message;
    private int statusCode;
    private String timestamp;
    
    public CustomResponse() {}
    
	public CustomResponse(String message, int statusCode, String timestamp) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}

