package org.educa.app;

import org.educa.service.ExcelService;

import java.io.IOException;
public class Actividad6 {

    private static final String PATH = "/home/victor/Descargas/Excels/SuperHeroes.xlsx";

    public static void main(String[] args) throws IOException {
        ExcelService excelService = new ExcelService();
        excelService.createExcelFile(PATH);

    }
}
