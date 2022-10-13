package org.educa.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.educa.dao.FileDAOImpl;
import org.educa.entity.ComicInfoEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    FileDAOImpl fileDAO = new FileDAOImpl();

    public void createExcelFile(String path){
        // Cargar Info
        List<ComicInfoEntity> comicInfoEntityList = loadInfo();
        // Creando el workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Un workbook tiene hojas, por lo que me creo la hoja
            Sheet sheet = workbook.createSheet();
            createHeader(sheet, workbook.createCellStyle(), workbook.createFont());
            createRows(comicInfoEntityList, sheet, workbook.createCellStyle(), workbook.createFont());

            fileDAO.createExcelInDisk(workbook, path);
        } catch (IOException e) {
            System.err.println("Error creando workbook: " + e.getMessage());
        }
    }

    private List<ComicInfoEntity> loadInfo() {
        List<ComicInfoEntity> comicInfoEntityList = new ArrayList<>();
        comicInfoEntityList.add(new ComicInfoEntity("SpiderMan", "Marvel",
                "Stan Lee y Steve Ditko", "Amazing Fantasy #15", "Agosto de 1962"));
        comicInfoEntityList.add(new ComicInfoEntity("Superman", "DC",
                "Jerry Siegel y Joe Shuster", "Action Comics #1", "Junio de 1938"));
        comicInfoEntityList.add(new ComicInfoEntity("Batman", "DC",
                "Bob Kane y Bill Finger", "Detective Comics #27", "Marzo de 1939"));
        comicInfoEntityList.add(new ComicInfoEntity("Iron Man", "Marvel",
                "Stan Lee, Larry Lieber, Don Heck y Jack Kirby", "Tales of Suspense #39",
                "Marzo de 1963"));
        return  comicInfoEntityList;
    }


    private static void createHeader(Sheet sheet, CellStyle cellStyle, Font font) {

        cellStyle.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        cellStyle.setFont(font);

        // Añado la primera fila
        Row row = sheet.createRow(0);
        // Creamos la celda en la que vamos a incluir la información
        Cell cell = row.createCell(0);
        // Se crea el contenido y se mete en la celda.
        RichTextString text = new XSSFRichTextString("Super Héroe");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        text = new XSSFRichTextString("Compañía");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        text = new XSSFRichTextString("Creador");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        text = new XSSFRichTextString("Primera Aparición");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        text = new XSSFRichTextString("Fecha Aparición");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private void createRows(List<ComicInfoEntity> comicInfoEntityList, Sheet sheet, CellStyle cellStyle, Font font) {

        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setColor(IndexedColors.BLACK.getIndex());
        font.setBold(false);
        cellStyle.setFont(font);

        for (int i = 0; i < comicInfoEntityList.size(); i++) {
            ComicInfoEntity comicInfoEntity = comicInfoEntityList.get(i);
            Row row = sheet.createRow(i + 1);
            // Creamos la celda 0 en la que vamos a incluir la información sobre el super heroe
            Cell cell = row.createCell(0);
            // Se crea el contenido y se mete en la celda.
            RichTextString text = new XSSFRichTextString(comicInfoEntity.getSuperHero());
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);

            // Creamos la celda 0 en la que vamos a incluir la información sobre la compañia
            cell = row.createCell(1);
            // Se crea el contenido y se mete en la celda.
            text = new XSSFRichTextString(comicInfoEntity.getCompany());
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);

            // Creamos la celda 0 en la que vamos a incluir la información sobre el creador
            cell = row.createCell(2);
            // Se crea el contenido y se mete en la celda.
            text = new XSSFRichTextString(comicInfoEntity.getCreator());
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);

            // Creamos la celda 0 en la que vamos a incluir la información sobre la primera aparición
            cell = row.createCell(3);
            // Se crea el contenido y se mete en la celda.
            text = new XSSFRichTextString(comicInfoEntity.getFirstApparition());
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);

            // Creamos la celda 0 en la que vamos a incluir la información sobre el fecha de aparición
            cell = row.createCell(4);
            // Se crea el contenido y se mete en la celda.
            text = new XSSFRichTextString(comicInfoEntity.getDateApparition());
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);
        }
    }

}
