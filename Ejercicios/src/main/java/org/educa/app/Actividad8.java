package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;

public class Actividad8 {

    private final static String ARCHIVO_FICHEROS = "/home/victor/Documentos/Archivo_Ficheros.txt";
    private final static String ARCHIVO_DIRECTORIOS = "/home/victor/Documentos/Archivo_Directorios.txt";

    public static void main(String[] args) throws IOException {
        String fichero_eje7 = args[0];
        FileService fileService = new FileService();
        fileService.fileAndDirectorySeparator(fichero_eje7, ARCHIVO_FICHEROS, ARCHIVO_DIRECTORIOS);
    }
}
