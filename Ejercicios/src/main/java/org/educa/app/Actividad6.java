package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;


public class Actividad6 {

    private static final String PATH = "C:\\Users\\FX516\\Downloads\\Actividad6.xlsx";

    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        fileService.administradorExcel(PATH);
    }
}
