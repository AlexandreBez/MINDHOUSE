package com.emailServer.objects;

public class FileShareHelper {

	private String email;
	private String fileName;
	private String filePath;
	
	public FileShareHelper() {}
	
	public FileShareHelper(String email,String fileName, String filePath) {
		super();
		this.email = email;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
