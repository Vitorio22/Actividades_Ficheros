package org.educa.dao;

import org.apache.poi.ss.usermodel.Workbook;
import org.educa.entity.FileInfoEntity;

import java.io.*;
import java.util.List;

public interface FileDAO {

    File[] listFiles(String directory);

    void rename(File file, String name);

    void insertTextIntoFile(File file, String text);

    void readTextInFile(File file, String text);

    void insertIntoDataStreamFile(int numero, File file) throws IOException;

    void showDataStreamFile(File file);

    void muestraContenidoDelFicheroConStream(FileInputStream lecturaFichero) throws IOException;

    void insertaEnteroEnFicheroConStream(int numero, FileOutputStream fichero) throws IOException;

    long getIntegersInFile(File file) throws IOException;

    int getIntegerInPosition(int i, File file) throws IOException;

    void modifyIntegerInDataStreamFile(int newNumber, int i, File file) throws IOException;

    void createExcelInDisk(Workbook workbook, String path) throws IOException;

    List<FileInfoEntity> createInfoFile(File folderName);

    void crearListado(List<FileInfoEntity> fileInfoEntities, String nombreFicheroResultado);

    List<String> getLinesInFiles(File file) throws IOException;

    void writeInFile(List<String> directoryList, String ficheroDirectorios) throws IOException;

}
