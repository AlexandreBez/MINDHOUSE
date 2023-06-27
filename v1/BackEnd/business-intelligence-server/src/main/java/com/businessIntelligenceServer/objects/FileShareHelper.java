package com.businessIntelligenceServer.objects;

/**
 * The FileShareHelper class represents a helper object for file sharing operations.
 */
public class FileShareHelper {

    private String email;
    private String fileName;
    private String filePath;

    /**
     * Constructs an empty FileShareHelper object.
     */
    public FileShareHelper() {
    }

    /**
     * Constructs a FileShareHelper object with the specified email, file name, and file path.
     *
     * @param email     the email address associated with the file
     * @param fileName  the name of the file
     * @param filePath  the path to the file
     */
    public FileShareHelper(String email, String fileName, String filePath) {
        this.email = email;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * Retrieves the email address associated with the file.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the file.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the name of the file.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the name of the file.
     *
     * @param fileName the file name to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves the path to the file.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the path to the file.
     *
     * @param filePath the file path to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}