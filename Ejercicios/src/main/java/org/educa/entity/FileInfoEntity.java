package org.educa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileInfoEntity {

    private static final String SEPARATOR = ";";

    //file name
    private String fileName;
    //type F: File, D: Directory
    private String type;
    //type R: Read W: Write X: eXecute
    private List<String> permissions;
    //size in bytes
    private long size;


    public FileInfoEntity() {
        super();
        this.permissions = new ArrayList<>();
    }
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return the permissions
     */
    public List<String> getPermissions() {
        return permissions;
    }
    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    public String toPrint() {
        return getFileName()+SEPARATOR+getType()+SEPARATOR+ Arrays.toString(getPermissions().toArray())+SEPARATOR+getSize();
    }
}