package com.authenticationServer.objects;

public class TempPasswordRequest {
	
	private String id;
	private String new_password;
	private String confirm_password;
	  
	public TempPasswordRequest() {}

	public TempPasswordRequest(String id, String new_password, String confirm_password) {
		super();
		this.id = id;
		this.new_password = new_password;
		this.confirm_password = confirm_password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

}
