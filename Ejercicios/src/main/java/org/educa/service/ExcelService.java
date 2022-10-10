package org.educa.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.educa.dao.FileDAOImpl;
import org.educa.entity.SuperHero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    FileDAOImpl fileDAO = new FileDAOImpl();
    public void createExcelFile(String path) {
        List<SuperHero> superHero = loadInfo();
        try (Workbook workbook = new HSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            createHeader(sheet, workbook.createCellStyle(), workbook.createFont());
            createRows(superHero, sheet);
            fileDAO.createExcelInDisk(workbook, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRows(List<SuperHero> SuperHeroList, Sheet sheet) {
        for (int i = 0; i < SuperHeroList.size(); i++) {
            SuperHero SuperHero = SuperHeroList.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            RichTextString text = new XSSFRichTextString(SuperHero.getName());
            cell.setCellValue(text);

            cell = row.createCell(1);
            text = new XSSFRichTextString(SuperHero.getCompany());
            cell.setCellValue(text);

            cell = row.createCell(2);
            text = new XSSFRichTextString(SuperHero.getCreator());
            cell.setCellValue(text);

            cell = row.createCell(3);
            text = new XSSFRichTextString(SuperHero.getFirstApparition());
            cell.setCellValue(text);

            cell = row.createCell(4);
            text = new XSSFRichTextString(SuperHero.getDateApparition());
            cell.setCellValue(text);
        }
    }

    private void createHeader(Sheet sheet, CellStyle cellStyle, Font font) {
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        RichTextString text = new XSSFRichTextString("Super Héroe");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(1);
        text = new XSSFRichTextString("Compañia");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        text = new XSSFRichTextString("Creador");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        text = new XSSFRichTextString("Primera aparición");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        text = new XSSFRichTextString("Fecha de aparición");
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private List<SuperHero> loadInfo() {
        List<SuperHero> SuperHero = new ArrayList<>();
        SuperHero.add(new SuperHero("SpiderMan", "Marvel", "Stan Lee y Steve Ditko", "Amazing Fantasy #15", "Agosto de 1962"));
        SuperHero.add(new SuperHero("Superman", "DC", "Jerry Siegel y Joe Shuster", "Action Comics #1", "Junio de 1938"));
        SuperHero.add(new SuperHero("Batman", "DC", "Bob Kane y Bill Finger", "Detective Comics #27", "Marzo de 1939"));
        SuperHero.add(new SuperHero("Iron Man", "Marvel", "Stan Lee, Larry Lieber, Don Heck y Jack Kirby", "Tales of Suspense #39", "Marzo de 1963"));
        return SuperHero;
    }
}
