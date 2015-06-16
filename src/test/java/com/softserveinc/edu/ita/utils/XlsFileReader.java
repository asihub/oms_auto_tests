package com.softserveinc.edu.ita.utils;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.softserveinc.edu.ita.utils.PropertyLoader.getProperty;

public final class XlsFileReader {

    private final static String PROPERTY_FILE_NAME = "testDataXlsFile";

    private static HSSFSheet sheet;
    private static HSSFWorkbook workbook;
    private static File excelFile;
    private static FileInputStream fileInputStream;

    private XlsFileReader() {
    }

    /**
     * This method reads all rows from a specific sheet from xls file, skipping the first row with column names.
     *
     * @param sheetName - name of the sheet
     */
    public static Object[][] readFromXlsByRows(String sheetName) throws IOException {
        excelFile = new File(getProperty(PROPERTY_FILE_NAME));

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet(sheetName);

        final int numberOfRows = sheet.getLastRowNum();
        final int numberOfColumns = sheet.getRow(0).getLastCellNum();

        final String[][] xlsData = new String[numberOfRows][numberOfColumns];
        String cellValue;

        for (int i = 1; i <= numberOfRows; i++) {
            final HSSFRow row = sheet.getRow(i);

            for (int j = 0; j < numberOfColumns; j++) {
                final HSSFCell cell = row.getCell(j);
                final int cellType = cell.getCellType();

                if (cellType == HSSFCell.CELL_TYPE_FORMULA) {
                    throw new RuntimeException("Cannot process a formula. Please change field to result of formula.");
                } else {
                    cellValue = String.valueOf(cell);
                    xlsData[i - 1][j] = cellValue;
                }
            }
        }
        return xlsData;
    }


    /**
     * This method reads a column from specific sheet from xls file, skipping the first row with column names.
     *
     * @param sheetName  - name of the sheet
     * @param columnName - name of the column
     */
    public static List<String> readFromXlsByColumns(String sheetName, String columnName) throws IOException {
        excelFile = new File(getProperty(PROPERTY_FILE_NAME));

        try {
            fileInputStream = new FileInputStream(excelFile);
            workbook = new HSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = workbook.getSheet(sheetName);

        final List<String> xlsData = new ArrayList<>();

        Integer columnNumber = null;

        final Row firstRow = sheet.getRow(0);

        for (Cell cell : firstRow) {
            if (cell.getStringCellValue().equals(columnName)) {
                columnNumber = cell.getColumnIndex();
            }
        }

        if (columnNumber != null) {
            for (Row row : sheet) {
                final Cell cell = row.getCell(columnNumber);
                if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    break;
                } else {
                    xlsData.add(cell.toString());
                }
            }
        }

        return xlsData.stream().skip(1).collect(Collectors.toList());
    }
}
