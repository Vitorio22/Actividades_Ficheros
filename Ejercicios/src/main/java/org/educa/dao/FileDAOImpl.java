package org.educa.dao;

import org.apache.poi.ss.usermodel.*;
import org.educa.entity.FileInfoEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
    public void createExcelInDisk(Workbook workbook, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            workbook.write(fileOutputStream);
            System.out.println("Excel creado en " + path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FileInfoEntity> createInfoFile(File folderName) {
        List<FileInfoEntity> fileInfoEntityList = new ArrayList<>();
        for (File file : folderName.listFiles()) {
            FileInfoEntity fileInfoEntity = new FileInfoEntity();
            fileInfoEntity.setFileName(file.getName());
            fileInfoEntity.setType(file.isDirectory() ? "Directorio" : "Fichero");
            if (file.canExecute()) {
                fileInfoEntity.getPermissions().add("Ejecutable");
            }
            if (file.canRead()) {
                fileInfoEntity.getPermissions().add("Lectura");
            }
            if (file.canWrite()) {
                fileInfoEntity.getPermissions().add("Escritura");
            }
            fileInfoEntity.setSize(file.length());
            fileInfoEntityList.add(fileInfoEntity);

        }
        return fileInfoEntityList;
    }

    @Override
    public void crearListado(List<FileInfoEntity> fileInfoEntities, String nombreFicheroResultado) {

        File ficheroResultado = new File(nombreFicheroResultado);
        try (PrintWriter pw = new PrintWriter(ficheroResultado)) {
            if (!ficheroResultado.exists()) {
                ficheroResultado.createNewFile();
            }
            for (FileInfoEntity fileInfoEntity : fileInfoEntities) {
                System.out.println(fileInfoEntity.toPrint());
                pw.println(fileInfoEntity.toPrint());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> filterFilesAndDirectories(File fichero) throws IOException {
        List<String> listResults = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                listResults.add(linea);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listResults;
    }

    @Override
    public void createInfoFile(List<String> fileList, String archivoFicheros) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(archivoFicheros), StandardCharsets.ISO_8859_1))) {
            for (String file : fileList) {
                File fichero = new File(file);
                if (fichero.exists()) {
                    pw.println(file);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



