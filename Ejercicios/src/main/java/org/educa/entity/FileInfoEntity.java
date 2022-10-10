package org.educa.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileInfoEntity {

    public static final String SEPARATOR = ";";
    private String fileName;
    private String type;
    private List<String> permissions;
    private long size;

    public FileInfoEntity(String fileName, String type, List<String> permissions, long size) {
        this.fileName = fileName;
        this.type = type;
        this.permissions = permissions;
        this.size = size;
    }

    public FileInfoEntity() {
        super();
        this.permissions = new ArrayList<>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toPrint() {
        return fileName + SEPARATOR + type + SEPARATOR + Arrays.toString(getPermissions().toArray()) + SEPARATOR + size;
    }
}
