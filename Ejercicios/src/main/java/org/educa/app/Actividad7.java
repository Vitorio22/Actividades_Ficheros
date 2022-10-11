package org.educa.app;

import org.educa.service.FileService;

public class Actividad7 {

    private static final String NOMBRE_FICHERO_RESULTADO = "C:\\Users\\FX516\\Documents\\Ejercicio7Resultado\\resultado.txt";

    public static void main(String[] args) {
        String folderName = args[0];
        FileService fileService = new FileService();
        fileService.createFileWithAllFilesInFolder(folderName, NOMBRE_FICHERO_RESULTADO);

    }
}
