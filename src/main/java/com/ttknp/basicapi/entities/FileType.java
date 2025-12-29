package com.ttknp.basicapi.entities;

public class FileType {
    
    private String fileExtension;

    public FileType() {
    }

    public FileType(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FileType{");
        sb.append("fileType='").append(fileExtension).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
