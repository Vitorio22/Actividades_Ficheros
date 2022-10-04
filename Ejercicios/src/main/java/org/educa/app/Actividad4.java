package org.educa.app;

import org.educa.service.FileService;

public class Actividad4 {

    private static final String PATH = "C:\\temp\\ad\\ut2\\actividad4\\enteros.dat";


    public static void main(String[] args) {
        FileService fileService = new FileService();
        fileService.insertIntegerInDataStreamFile(PATH);

    }

}
