package org.educa.app;

import org.educa.service.FileService;

public class Actividad1 {

    private static final String PATH = "C:\\temp\\ad\\ut2\\actividad1";

    public static void main(String[] args) {
        FileService fileService = new FileService();
        fileService.listFiles(PATH);

    }

}
