package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;

public class Actividad4_ConStream {

        private static final String PATH = "C:\\Users\\FX516\\Downloads\\enteros.dat";
        private static final String PATH_COP = "C:\\Users\\FX516\\Downloads\\enteros_cop_num.dat";

        public static void main(String[] args) throws IOException {
            FileService fileService = new FileService();
            fileService.insertaEnteroEnFicheroConStream(PATH, PATH_COP);
        }
}
