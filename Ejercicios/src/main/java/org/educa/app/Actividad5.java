package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;

public class Actividad5 {

        private static final String PATH = "C:\\Users\\FX516\\Downloads\\Ejercicio5\\enteros.dat";

        public static void main(String[] args) {
            FileService fileService = new FileService();
            fileService.modifyIntegerInDataStreamFile(PATH);
        }
}
