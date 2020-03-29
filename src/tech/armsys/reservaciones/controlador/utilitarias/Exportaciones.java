package tech.armsys.reservaciones.controlador.utilitarias;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tech.armsys.reservaciones.modelo.Reserva;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Exportaciones {
    public String excelRuta = "src/tech/armsys/reservaciones/vista/Exportaciones/exportLibro.xlsx";//ruta del documento a exportar
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public boolean exportarAExcel(List<Reserva> listaReservas, String nombreMes, int anio){
        int rowCount = 0;
        int i=0;
        Row row;
        workbook = new XSSFWorkbook();//se inicializa el objeto de libro de excel
        sheet = workbook.createSheet("Reservaciones_"+nombreMes+"-"+anio);//se inicializa el objeto de hoja de excel con el nombre de la misma

        //Se escriben y formatean los títulos de cada columna
        row = sheet.createRow(++rowCount);
        formatearHojaExcel(nombreMes, anio, row);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        for (Reserva reserva : listaReservas) {//por cada reserva en la lista, se crea una nueva fila y se escribe en ella el contenido de la reserva
            row = sheet.createRow(++rowCount);
            escribirHojaExcel(reserva, row);
        }
        try (FileOutputStream outputStream = new FileOutputStream(excelRuta)) {//se intenta exportar al documento externo
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void formatearHojaExcel(String nombreMes, int anio, Row row){
        //Se define el estilo de las celdas de título
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(false);
        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);

        Cell cell = row.createCell(1);
        cell.setCellValue("      Nombre de espacio      ");
        cell.setCellStyle(cellStyle);
        cell = row.createCell(2);
        cell.setCellValue("       Horario       ");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue("       Fecha       ");
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue("       Nombre de usuario       ");
        cell.setCellStyle(cellStyle);
    }

    private void escribirHojaExcel(Reserva reserva, Row row) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //Se crean entonces las celdas con contenido
        Cell cell = row.createCell(1);
        cell.setCellValue(reserva.getNombre_espacio());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(reserva.getHorario());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(3);
        cell.setCellValue(reserva.getFecha());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(4);
        cell.setCellValue(reserva.getNombre_usuario());
        cell.setCellStyle(cellStyle);
    }


}
