package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;
public class Actividad6 {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileService();
        fileService.administradorExcel();
    }
}
