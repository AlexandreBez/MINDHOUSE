package com.businessServer.objects;

public class ListOfEmails {
	
    private String[] emails;
    
    public ListOfEmails() {}
    
    public ListOfEmails(String[] emails) {
        super();
        this.emails = emails;
    }

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}
    
    
}
