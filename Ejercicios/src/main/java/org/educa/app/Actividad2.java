package org.educa.app;

import org.educa.exception.FileWithoutExtensionException;
import org.educa.service.FileService;

public class Actividad2 {

    private static final String PATH = "C:\\temp\\ad\\ut2\\actividad1";

    public static void main(String[] args) throws FileWithoutExtensionException {
        FileService fileService = new FileService();
        fileService.renameFilesWithPermissions(PATH);

    }

}
