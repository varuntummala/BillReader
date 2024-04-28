import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BillReader {

    public static void main(String[] args) {
        try {
            File file = new File("/Users/varuntummala/Desktop/Varun/Verizon/New/MyBill_20240325.pdf");
            PDDocument document = PDDocument.load(file);

            // Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Retrieving text from PDF document
            String text = pdfStripper.getText(document);

            // Printing the text
            System.out.println(text);

            String[] data = text.split("\\s+");

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("pdf content");

            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);

                cell.setCellValue(data[i]);
            }

            FileOutputStream outputStream = new FileOutputStream("/Users/varuntummala/Desktop/Varun/Verizon/New/MyBill_20240325.xlsx");
            workbook.write(outputStream);

            // Closing the workbook
            workbook.close();

            // Closing the document
            document.close();

            System.out.println("PDF content written to Excel successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}