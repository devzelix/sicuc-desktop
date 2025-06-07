package com.culturacarabobo.sicuc.desktop.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public static void exportJTableToExcel(JTable table, String filePath) throws IOException {
        // Convert colors from ColorPalette to XSSFColor for Apache POI
        byte[] rgbTitleColor = new byte[] { (byte) ColorPalette.TEXT_COLOR.getRed(),
                (byte) ColorPalette.TEXT_COLOR.getGreen(), (byte) ColorPalette.TEXT_COLOR.getBlue() };
        XSSFColor titleColor = new XSSFColor(rgbTitleColor, null);

        byte[] rgbHeaderColor = new byte[] { (byte) ColorPalette.PRIMARY_COLOR.getRed(),
                (byte) ColorPalette.PRIMARY_COLOR.getGreen(), (byte) ColorPalette.PRIMARY_COLOR.getBlue() };
        XSSFColor headerColor = new XSSFColor(rgbHeaderColor, null);

        byte[] rgbDetailsColor = new byte[] { (byte) ColorPalette.TERTIARY_COLOR.getRed(),
                (byte) ColorPalette.TERTIARY_COLOR.getGreen(), (byte) ColorPalette.TERTIARY_COLOR.getBlue() };
        XSSFColor detailsColor = new XSSFColor(rgbDetailsColor, null);

        // Create workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Registro de Cultores");

        // Configure fonts for title and cell text
        XSSFFont titleFont = (XSSFFont) workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBold(true);
        titleFont.setColor(titleColor);

        XSSFFont textFont = (XSSFFont) workbook.createFont();
        textFont.setFontName("Arial");
        textFont.setFontHeightInPoints((short) 12);
        textFont.setBold(false);
        textFont.setColor(detailsColor);

        // Header style for column headers
        XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
        headerStyle.setFont(titleFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(headerColor);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setTopBorderColor(detailsColor);
        headerStyle.setRightBorderColor(detailsColor);
        headerStyle.setBottomBorderColor(detailsColor);
        headerStyle.setLeftBorderColor(detailsColor);

        // Create header row and set column names
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(25f);
        for (int col = 0; col < table.getColumnCount(); col++) {
            XSSFCell cell = (XSSFCell) headerRow.createCell(col);
            cell.setCellValue(table.getColumnName(col));
            cell.setCellStyle(headerStyle);
        }

        // Cell style for data cells
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        cellStyle.setFont(textFont);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setTopBorderColor(detailsColor);
        cellStyle.setRightBorderColor(detailsColor);
        cellStyle.setBottomBorderColor(detailsColor);
        cellStyle.setLeftBorderColor(detailsColor);

        // Fill rows with data from the JTable
        for (int row = 0; row < table.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            excelRow.setHeightInPoints(23f);
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object value = table.getValueAt(row, col);
                Cell cell = excelRow.createCell(col);
                cell.setCellValue(value != null ? value.toString() : "");
                cell.setCellStyle(cellStyle);
            }
        }

        // Auto-size columns with some extra padding
        for (int i = 0; i < table.getColumnCount(); i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 3 * 256);
        }

        // Write the workbook to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Close the workbook to free resources
        workbook.close();
    }
}
