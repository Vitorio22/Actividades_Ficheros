package org.educa.app;

import org.educa.service.FileService;

import java.io.IOException;

public class Actividad5 {

        private static final String PATH = "/home/victor/Descargas/Archivo NumerosEnteros/enteros.dat";

        public static void main(String[] args) throws IOException {
            FileService fileService = new FileService();
            fileService.modificarFicheroConEntero(PATH);
        }
}
