package com.rsjava.reportgenerator.reports;

import com.rsjava.reportgenerator.model.Car;
import com.rsjava.reportgenerator.service.CarService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final CarService carService;
    private  String[] columnsHeadings = {"Id", "Marka", "Model", "Kolor", "Czy jest używany", "Data produkcji"};

    //cały plik
    private Workbook workbook;
    //zakładka
    private Sheet sheet;

    public void generateReport(){
        List<Car> cars = carService.getCars();

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Cars");

        //czcionka nagłówka
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.RED.index);

        //styl komórek
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

        //rząd nagłówkowy o indeksie zero
        Row headerRow = sheet.createRow(0);

        //wpisuje nazyy do rzędu nagówkowego i uzupełniam style
        for (int i = 0; i < columnsHeadings.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnsHeadings[i]);
            cell.setCellStyle(headerCellStyle);
        }

        //indeks 2 wiersza
        int rowNum = 1;

        //iteruję sie po liście i zapisuję poszczególne obiekty w kolejne komórki,
        //za każdą iteracją wiersz schodzi o jeden niżej
        for (Car car : cars) {
            Row row = sheet.createRow(rowNum ++);
            //kolumna zero w pierwszuym wierszu
            row.createCell(0).setCellValue(car.getId());
            //kolumna 2
            row.createCell(1).setCellValue(car.getBrand());
            row.createCell(2).setCellValue(car.getModel());
            row.createCell(3).setCellValue(car.getColor());
            row.createCell(4).setCellValue(car.getIsUsed());
            row.createCell(5).setCellValue(getFormattedDate(car.getBuildDate()));
        }

        for (int i = 0; i < columnsHeadings.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            FileOutputStream file = new FileOutputStream("cars.xlsx");
            workbook.write(file);
            file.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormattedDate(LocalDate localDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormatter.format(localDate);
    }
}
