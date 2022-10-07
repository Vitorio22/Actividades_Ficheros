package org.educa.dao;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

public interface FileDAO {

    File[] listFiles(String directory);

    void rename(File file, String name);

    void insertTextIntoFile(File file, String text);

    void readTextInFile(File file, String text);

    void insertIntoDataStreamFile(int numero, File file) throws IOException;

    void showDataStreamFile(File file);

    void muestraContenidoDelFicheroConStream(FileInputStream lecturaFichero) throws IOException;

    void insertaEnteroEnFicheroConStream(int numero, FileOutputStream fichero) throws IOException;

    void mostrarContenidoFicheroRandom(RandomAccessFile randomAccessFile) throws IOException;

    void modificarPosicionSeleccionada(RandomAccessFile fichero, int posicion, int entero);

    void insertData(Workbook book);

}
