package org.educa.app;

import org.educa.service.FileService;

public class Actividad8 {

    private final static String ARCHIVO_FICHEROS = "C:\\Users\\FX516\\Documents\\Archivo_Ficheros.txt";
    private final static String ARCHIVO_DIRECTORIOS = "C:\\Users\\FX516\\Documents\\Archivo_Directorios.txt";

    public static void main(String[] args) {
        String fichero_eje7 = args[0];
        FileService fileService = new FileService();
        fileService.splitFilesDirectories(fichero_eje7, ARCHIVO_FICHEROS, ARCHIVO_DIRECTORIOS);
    }
}
