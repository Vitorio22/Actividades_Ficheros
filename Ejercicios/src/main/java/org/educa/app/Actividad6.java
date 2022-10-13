package org.educa.app;

import org.educa.service.ExcelService;

public class Actividad6 {

    private static final String PATH = "C:\\Users\\FX516\\Documents\\Ejercicio6\\Superheroes.xlsx";

    public static void main(String[] args) {
        ExcelService excelService = new ExcelService();
        excelService.createExcelFile(PATH);
    }
}
