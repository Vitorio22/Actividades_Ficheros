package org.educa.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.educa.dao.FileDAO;
import org.educa.dao.FileDAOImpl;
import org.educa.entity.FileEntity;
import org.educa.exception.FileWithoutExtensionException;

import java.io.*;
import java.util.Scanner;

public class FileService {

    FileDAO fileDAO = new FileDAOImpl();

    public void listFiles(String path) {
        File[] files = fileDAO.listFiles(path);
        for (File file : files) {
            FileEntity fileEntity = new FileEntity(file.getName());
            if (file.isDirectory()) {
                fileEntity.setType("directorio");
            } else {
                fileEntity.setType("fichero");
            }
            System.out.println("El fichero " + fileEntity.getName() + " es un " + fileEntity.getName());
        }
    }

    public void renameFilesWithPermissions(String path) throws FileWithoutExtensionException {
        File[] files = fileDAO.listFiles(path);
        for (File file : files) {
            FileEntity fileEntity = new FileEntity(file.getName());
            setPermissions(fileEntity, file);
            if (!fileEntity.getPermissions().equals("_")) {
                String[] extensionAndPath = getExtensionAndPathFromFile(file);
                fileDAO.rename(file, extensionAndPath[1] + fileEntity.getPermissions() + extensionAndPath[0]);
            }
        }
    }

    private String[] getExtensionAndPathFromFile(File file) throws FileWithoutExtensionException {
        String[] retorno = new String[2];
        int i = file.getAbsolutePath().lastIndexOf(".");
        if (i > 0) {
            //extension
            retorno[0] = file.getAbsolutePath().substring(i);
            retorno[1] = file.getAbsolutePath().substring(0, i);
        } else {
            throw new FileWithoutExtensionException("El fichero no tiene extension");
        }
        return retorno;
    }

    private void setPermissions(FileEntity fichero, File file) {
        String permissions = "_";
        permissions = file.canRead() ? permissions + "R" : permissions;
        permissions = file.canWrite() ? permissions + "W" : permissions;
        permissions = file.canRead() ? permissions + "X" : permissions;
        fichero.setPermissions(permissions);
    }

    public void insertTextInFile(String path) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce un nombre para el fichero: ");
            String fileName = sc.nextLine();
            File file = new File(path + fileName + ".txt");
            if (file.createNewFile()) {
                System.out.println("Introduce un texto: ");
                String text = sc.nextLine();
                fileDAO.insertTextIntoFile(file, text);
                fileDAO.readTextInFile(file, text);
            } else {
                System.out.println("The file cannot be created");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntegerInDataStreamFile(String path) {
        try (Scanner sc = new Scanner(System.in)) {
            File file = new File(path);
            fileDAO.showDataStreamFile(file);
            System.out.println("Introduce un numero entero: ");
            int numero = sc.nextInt();
            fileDAO.insertIntoDataStreamFile(numero, file);
            fileDAO.showDataStreamFile(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int pideEntero() {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce un numero entero: ");
            return sc.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int pidePosicion(RandomAccessFile fichero) {

        try (Scanner sc = new Scanner(System.in)) {
            long size = fichero.length();
            size = size / 4;
            System.out.println("Introduce una posicion entre 1 y " + size + " para modificar el numero: ");
            int posicion = sc.nextInt();
            return posicion - 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insertaEnteroEnFicheroConStream(String path, String pathCop) throws IOException {

        FileInputStream lecturaFichero = new FileInputStream(path);
        int[] ficheroEnt = new int[lecturaFichero.available()];
        fileDAO.muestraContenidoDelFicheroConStream(lecturaFichero, ficheroEnt);
        FileOutputStream escrituraFicheroNuevo = new FileOutputStream(pathCop, true);
        int numero = pideEntero();
        fileDAO.insertaEnteroEnFicheroConStream(numero, escrituraFicheroNuevo, ficheroEnt);
        FileInputStream lecturaFicheroNuevo = new FileInputStream(pathCop);
        fileDAO.muestraContenidoDelFicheroFinalConStream(lecturaFicheroNuevo);
    }

    public void modificarFicheroConEntero(String path) {

        try {
            RandomAccessFile fichero = new RandomAccessFile(path, "rw");
            fileDAO.mostrarContenidoFicheroRandom(fichero);
            int posicion = pidePosicion(fichero);
            int numero = pideEntero();
            fileDAO.modificarPosicionSeleccionada(fichero, posicion, numero);
            fileDAO.mostrarContenidoFicheroRandom(fichero);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void administradorExcel(String path) throws IOException {

        Workbook book = new XSSFWorkbook();
        book.write(new FileOutputStream(path));
        fileDAO.insertRowAndColumns(book);


    }
}

