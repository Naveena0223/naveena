package emp_excel; 
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import java.io.FileOutputStream;
import java.io.IOException;

public class Empdetails {
    public static void main(String[] args) {
        try (Workbook workbook = new HSSFWorkbook(); 
             FileOutputStream fileOut = new FileOutputStream("employee_data.xls")) {
            		Sheet sheet = workbook.createSheet("Employee Data");
            		String[] headers = {"EmpId", "EmpName", "EmpSalary", "DateOfJoining"};
            		Row headerRow = sheet.createRow(0);
            		for (int i = 0; i < headers.length; i++) {
            				Cell cell = headerRow.createCell(i);
            				cell.setCellValue(headers[i]);
            		}
            Object[][] employeeData = {
                    {101, "Naveena", 60000, "2022-05-01"},
                    {102, "Priya", 55000, "2021-04-12"},
                    {103, "Aakanksha", 50000, "2023-02-19"},
                    {104, "Jennifer", 45000, "2023-07-16"},
                    {103, "Sanju", 50000, "2022-10-22"}
            };
            int rowNum = 1;
            for (Object[] emp : employeeData) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < emp.length; i++) {
                    if (emp[i] instanceof Integer) {
                        row.createCell(i).setCellValue((Integer) emp[i]);
                    } else {
                        row.createCell(i).setCellValue(emp[i].toString());
                    }
                }
            }
            workbook.write(fileOut);
            System.out.println("Excel file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}