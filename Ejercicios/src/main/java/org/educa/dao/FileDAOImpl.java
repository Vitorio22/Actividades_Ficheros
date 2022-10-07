package org.educa.dao;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;

public class FileDAOImpl implements FileDAO {

    @Override
    public File[] listFiles(String directorio) {
        File path = new File(directorio);
        return path.listFiles();
    }

    @Override
    public void rename(File file, String name) {
        file.renameTo(new File(name));
    }

    @Override
    public void insertTextIntoFile(File file, String text) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(text);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readTextInFile(File file, String text) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            System.out.printf("Lectura del fichero %s: \n", file.getName());
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showDataStreamFile(File file) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(0);
            while (!isEOF(randomAccessFile)) {
                int entero = randomAccessFile.readInt();
                System.out.println(entero);
                if (isEOF(randomAccessFile)) {
                    System.out.println("FIN de fichero");
                }
            }
        } catch (EOFException e) {
            System.err.println("FIN de fichero");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void insertIntoDataStreamFile(int number, File file) throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.writeInt(number);
        }
    }

    private static boolean isEOF(RandomAccessFile randomAccessFile) throws IOException {
        return randomAccessFile.length() == randomAccessFile.getFilePointer();
    }

    @Override
    public void muestraContenidoDelFicheroConStream(FileInputStream lecturaFichero) throws IOException {

        DataInputStream dataInputStream = new DataInputStream(lecturaFichero);
        while (dataInputStream.available() > 0) {
            int entero = dataInputStream.readInt();
            System.out.println(entero);
        }
    }

    @Override
    public void insertaEnteroEnFicheroConStream(int numero, FileOutputStream escrituraFichero) throws IOException {

        DataOutputStream escrituraFi = new DataOutputStream(escrituraFichero);
        escrituraFi.writeInt(numero);
        System.out.println("Se ha insertado el entero " + numero + " en el fichero");
    }

    @Override
    public void mostrarContenidoFicheroRandom(RandomAccessFile fichero) {

        try {
            int contPosicion = 0;
            fichero.seek(0);
            while (fichero.getFilePointer() < fichero.length()) {
                int entero = fichero.readInt();
                System.out.println(contPosicion + "- " + entero);
                contPosicion++;
            }
        } catch (EOFException e) {
            System.err.println("FIN de fichero");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        }

    @Override
    public void modificarPosicionSeleccionada(RandomAccessFile fichero, int posicion, int entero) {

        try {
            fichero.seek(posicion * 4L);
            fichero.writeInt(entero);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void insertData(Workbook book) {

        Sheet sheet = book.createSheet("Hoja_Ac_6");

        Font fontHeader = book.createFont();
        fontHeader.setColor(IndexedColors.WHITE.getIndex());

        Font fontBody = book.createFont();
        fontBody.setColor(IndexedColors.BLACK.getIndex());

        CellStyle styleHeader = book.createCellStyle();
        styleHeader.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        styleHeader.setFillPattern(FillPatternType.BIG_SPOTS);
        styleHeader.setFont(fontHeader);

        CellStyle styleFirst = book.createCellStyle();
        styleFirst.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        styleFirst.setFillPattern(FillPatternType.BIG_SPOTS);
        styleFirst.setFont(fontBody);

        CellStyle styleSecond = book.createCellStyle();
        styleSecond.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        styleSecond.setFillPattern(FillPatternType.BIG_SPOTS);
        styleSecond.setFont(fontBody);

        Row rowHeader = sheet.createRow(0);
        Cell cellHeader = rowHeader.createCell(0);
        cellHeader.setCellValue("Super Héroe");
        cellHeader.setCellStyle(styleHeader);

        cellHeader = rowHeader.createCell(1);
        cellHeader.setCellValue("Compañia");
        cellHeader.setCellStyle(styleHeader);

        cellHeader = rowHeader.createCell(2);
        cellHeader.setCellValue("Creador");
        cellHeader.setCellStyle(styleHeader);

        cellHeader = rowHeader.createCell(3);
        cellHeader.setCellValue("Primera aparición");
        cellHeader.setCellStyle(styleHeader);

        cellHeader = rowHeader.createCell(4);
        cellHeader.setCellValue("Fecha de aparición");
        cellHeader.setCellStyle(styleHeader);

        Row rowOne = sheet.createRow(1);
        Cell cell = rowOne.createCell(0);
        cell.setCellValue("Superman");
        cell.setCellStyle(styleFirst);

        cell = rowOne.createCell(1);
        cell.setCellValue("DC");
        cell.setCellStyle(styleFirst);

        cell = rowOne.createCell(2);
        cell.setCellValue("Jerry Siegel y Joe Shuster");
        cell.setCellStyle(styleFirst);

        cell = rowOne.createCell(3);
        cell.setCellValue("Action Comics #1");
        cell.setCellStyle(styleFirst);

        cell = rowOne.createCell(4);
        cell.setCellValue("Junio de 1938");
        cell.setCellStyle(styleFirst);

        Row rowTwo = sheet.createRow(2);
        cell = rowTwo.createCell(0);
        cell.setCellValue("Batman");
        cell.setCellStyle(styleSecond);

        cell = rowTwo.createCell(1);
        cell.setCellValue("DC");
        cell.setCellStyle(styleSecond);

        cell = rowTwo.createCell(2);
        cell.setCellValue("Bob Kane y Bill Finger");
        cell.setCellStyle(styleSecond);

        cell = rowTwo.createCell(3);
        cell.setCellValue("Detective Comics #27");
        cell.setCellStyle(styleSecond);

        cell = rowTwo.createCell(4);
        cell.setCellValue("Marzo de 1939");
        cell.setCellStyle(styleSecond);

        Row rowThree = sheet.createRow(3);
        cell = rowThree.createCell(0);
        cell.setCellValue("Iron Man");
        cell.setCellStyle(styleFirst);

        cell = rowThree.createCell(1);
        cell.setCellValue("Marvel");
        cell.setCellStyle(styleFirst);

        cell = rowThree.createCell(2);
        cell.setCellValue("Stan Lee, Larry Lieber, Don Heck y Jack Kirby");
        cell.setCellStyle(styleFirst);

        cell = rowThree.createCell(3);
        cell.setCellValue("Tales of Suspense #39");
        cell.setCellStyle(styleFirst);

        cell = rowThree.createCell(4);
        cell.setCellValue("Marzo de 1963");
        cell.setCellStyle(styleFirst);









    }


}

