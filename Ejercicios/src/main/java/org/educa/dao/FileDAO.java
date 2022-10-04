package org.educa.dao;

import java.io.*;

public interface FileDAO {

    File[] listFiles(String directory);

    void rename(File file, String name);

    void insertTextIntoFile(File file, String text);

    void readTextInFile(File file, String text);

    void insertIntoDataStreamFile(int numero, File file) throws IOException;

    void showDataStreamFile(File file);

    void insertaEnteroEnFicheroConStream(int numero, FileOutputStream fichero, int[] ficheroNuevo) throws IOException;

    void muestraContenidoDelFicheroConStream(FileInputStream lecturaFichero, int[] ficheroEnt) throws IOException;

    void muestraContenidoDelFicheroFinalConStream(FileInputStream lecturaFicheroFinal) throws IOException;

    void mostrarContenidoFicheroRandom(RandomAccessFile randomAccessFile) throws IOException;

    void modificarPosicionSeleccionada(RandomAccessFile fichero, int posicion, int entero);
}
