package com.hello.storage;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
	String rootPath = System.getProperty("user.home");
	File dir1 = new File(rootPath + File.separator + "tmpFilesUpload");

    private String location =dir1.getAbsolutePath();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
