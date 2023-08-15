package com.businessServer.objects;

public class FileShareHelper {

    private String[] emails;
    private String fileName;
    private String filePath;

    public FileShareHelper() {
    }

    public FileShareHelper(String[] emails, String fileName, String filePath) {
        super();
        this.emails = emails;
        this.fileName = fileName;
        this.filePath = filePath;
    }

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
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